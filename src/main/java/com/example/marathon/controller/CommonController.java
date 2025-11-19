package com.example.marathon.controller;

import com.example.marathon.api.ApiResponse;
import com.example.marathon.service.CompetitionService;
import com.example.marathon.dao.Competition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CommonController {

    private final CompetitionService competitionService;

    public CommonController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @GetMapping("/genders")
    public ApiResponse<List<Map<String, String>>> getGenders() {
        // Static list of genders
        List<Map<String, String>> levels = Arrays.asList(
                Map.of("label", "Male", "value", "Male"),
                Map.of("label", "Female", "value", "Female"));
        return ApiResponse.success(levels);
    }

    @GetMapping("/experience-levels")
    public ApiResponse<List<Map<String, String>>> getExperienceLevels() {
        // Static list of experience levels
        List<Map<String, String>> levels = Arrays.asList(
                Map.of("label", "Beginner", "value", "Beginner"),
                Map.of("label", "Intermediate", "value", "Intermediate"),
                Map.of("label", "Advanced", "value", "Advanced"),
                Map.of("label", "Professional", "value", "Professional"));
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
}
