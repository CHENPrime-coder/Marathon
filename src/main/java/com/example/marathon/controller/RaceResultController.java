package com.example.marathon.controller;

import com.example.marathon.api.ApiResponse;
import com.example.marathon.dto.result.RaceResultResponse;
import com.example.marathon.service.RaceResultService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/results")
public class RaceResultController {

    private final RaceResultService service;

    public RaceResultController(RaceResultService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<RaceResultResponse>> query(@RequestParam(required = false) Integer competitionId,
            @RequestParam(required = false) String gender) {
        return ApiResponse.success(service.query(competitionId, gender));
    }

    @GetMapping("/{id}")
    public ApiResponse<RaceResultResponse> getById(@PathVariable Integer id) {
        RaceResultResponse response = service.getById(id);
        if (response == null) {
            return new ApiResponse<>(404, "Result not found", null);
        }
        return ApiResponse.success(response);
    }
}
