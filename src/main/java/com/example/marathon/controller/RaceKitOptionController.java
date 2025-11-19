package com.example.marathon.controller;

import com.example.marathon.api.ApiResponse;
import com.example.marathon.service.RaceKitOptionService;
import com.example.marathon.dao.RaceKitOption;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/race-kit-options")
public class RaceKitOptionController {
    private final RaceKitOptionService service;

    public RaceKitOptionController(RaceKitOptionService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<RaceKitOption>> list() {
        return ApiResponse.success(service.list());
    }
}
