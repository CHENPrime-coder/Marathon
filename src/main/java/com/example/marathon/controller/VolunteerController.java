package com.example.marathon.controller;

import com.example.marathon.api.ApiResponse;
import com.example.marathon.dto.volunteer.VolunteerRequest;
import com.example.marathon.service.VolunteerService;
import com.example.marathon.table.Volunteer;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/volunteers")
public class VolunteerController {

    private final VolunteerService service;

    public VolunteerController(VolunteerService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<Volunteer>> query(@RequestParam(required = false) Integer cityId,
                                              @RequestParam(required = false) String gender,
                                              @RequestParam(required = false) String keyword) {
        return ApiResponse.success(service.query(cityId, gender, keyword));
    }

    @PostMapping
    public ApiResponse<Volunteer> create(@RequestBody @Valid VolunteerRequest request) {
        request.setId(null);
        return ApiResponse.success(service.save(request));
    }

    @PostMapping("/import")
    public ApiResponse<Integer> importCsv(@RequestPart("file") MultipartFile file) {
        int count = service.importCsv(file);
        return ApiResponse.success(count);
    }
}
