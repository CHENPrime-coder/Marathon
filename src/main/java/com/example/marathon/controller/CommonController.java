package com.example.marathon.controller;

import com.example.marathon.api.ApiResponse;
import com.example.marathon.service.CompetitionService;
import com.example.marathon.dao.Competition;
import com.example.marathon.service.FileStorageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CommonController {

    private final CompetitionService competitionService;
    private final FileStorageService fileStorageService;

    public CommonController(CompetitionService competitionService, FileStorageService fileStorageService) {
        this.competitionService = competitionService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/genders")
    public ApiResponse<List<Map<String, String>>> getGenders() {
        // Static list of genders
        List<Map<String, String>> levels = Arrays.asList(
                Map.of("label", "男性", "value", "Male"),
                Map.of("label", "女性", "value", "Female"));
        return ApiResponse.success(levels);
    }

    @GetMapping("/experience-levels")
    public ApiResponse<List<Map<String, String>>> getExperienceLevels() {
        // Static list of experience levels
        List<Map<String, String>> levels = Arrays.asList(
                Map.of("label", "初次尝试", "value", "First time"),
                Map.of("label", "有一些经验", "value", "Have some experience"),
                Map.of("label", "经验丰富的", "value", "Experienced"));
        return ApiResponse.success(levels);
    }

    @GetMapping("/events")
    public ApiResponse<List<Map<String, Object>>> getEvents() {
        List<Competition> competitions = competitionService.list();
        List<Map<String, Object>> events = competitions.stream()
                .map(c ->
                        Map.<String, Object>of("label", c.getName(), "value", c.getId()))
                .collect(Collectors.toList());
        return ApiResponse.success(events);
    }

    @PostMapping("/upload")
    public ApiResponse<String> upload(@RequestPart("file") MultipartFile file) {
        String url = fileStorageService.upload(file);
        return ApiResponse.success(url);
    }
}
