package com.example.marathon.controller;

import com.example.marathon.api.ApiResponse;
import com.example.marathon.dto.runner.RunnerCreateRequest;
import com.example.marathon.dto.runner.RunnerUpdateRequest;
import com.example.marathon.service.RunnerService;
import com.example.marathon.table.Runner;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/runners")
public class RunnerController {

    private final RunnerService runnerService;

    public RunnerController(RunnerService runnerService) {
        this.runnerService = runnerService;
    }

    @PostMapping
    public ApiResponse<Void> create(@RequestBody @Valid RunnerCreateRequest request) {
        runnerService.createRunner(request);
        return ApiResponse.success();
    }

    @GetMapping("/{email}")
    public ApiResponse<Runner> get(@PathVariable String email) {
        return ApiResponse.success(runnerService.getByEmail(email));
    }

    @PutMapping("/{email}")
    public ApiResponse<Void> update(@PathVariable String email, @RequestBody @Valid RunnerUpdateRequest request) {
        runnerService.updateRunner(email, request);
        return ApiResponse.success();
    }

    @PostMapping("/{email}/avatar")
    public ApiResponse<String> upload(@PathVariable String email, @RequestPart("file") MultipartFile file) {
        String url = runnerService.updateAvatar(email, file);
        return ApiResponse.success(url);
    }

    @GetMapping
    public ApiResponse<List<Runner>> listAll(@RequestParam(required = false) Integer cityId,
                                             @RequestParam(required = false) String gender,
                                             @RequestParam(required = false) String keyword) {
        return ApiResponse.success(runnerService.query(cityId, gender, keyword));
    }
}
