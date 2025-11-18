package com.example.marathon.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {
    private final Path baseDir = Paths.get("uploads").toAbsolutePath();

    public FileStorageService() {
        try {
            Files.createDirectories(baseDir);
        } catch (IOException e) {
            throw new RuntimeException("Failed to init upload dir", e);
        }
    }

    public String saveAvatar(String email, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String filename = email + "-" + UUID.randomUUID() + (ext != null ? "." + ext : "");
        Path target = baseDir.resolve(filename);
        try {
            file.transferTo(target);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file", e);
        }
        return "/files/" + filename;
    }
}
