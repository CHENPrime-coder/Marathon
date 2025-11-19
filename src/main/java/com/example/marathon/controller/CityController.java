package com.example.marathon.controller;

import com.example.marathon.api.ApiResponse;
import com.example.marathon.service.CityService;
import com.example.marathon.dao.City;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ApiResponse<List<City>> list() {
        return ApiResponse.success(cityService.list());
    }
}
