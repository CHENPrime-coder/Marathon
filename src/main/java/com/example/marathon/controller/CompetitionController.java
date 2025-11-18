package com.example.marathon.controller;

import com.example.marathon.api.ApiResponse;
import com.example.marathon.service.CompetitionService;
import com.example.marathon.table.Competition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/competitions")
public class CompetitionController {
    private final CompetitionService competitionService;

    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @GetMapping
    public ApiResponse<List<Competition>> list() {
        return ApiResponse.success(competitionService.list());
    }
}
