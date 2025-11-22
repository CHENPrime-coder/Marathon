package com.example.marathon.controller;

import com.example.marathon.api.ApiResponse;
import com.example.marathon.api.PageResponse;
import com.example.marathon.dto.runner.RunnerCreateRequest;
import com.example.marathon.dto.runner.RunnerDetails;
import com.example.marathon.dto.runner.RunnerResponse;
import com.example.marathon.dto.runner.RunnerUpdateRequest;
import com.example.marathon.security.AuthContext;
import com.example.marathon.service.RunnerService;
import com.example.marathon.dao.Runner;
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
    public ApiResponse<RunnerDetails> get(@PathVariable String email) {
        return ApiResponse.success(runnerService.getDetailsByEmail(email));
    }

    @PutMapping("/update")
    public ApiResponse<Void> update(@RequestBody @Valid RunnerUpdateRequest request) {
        runnerService.updateRunner(AuthContext.getEmail(), request);
        return ApiResponse.success();
    }

    @GetMapping
    public ApiResponse<PageResponse<RunnerResponse>> listAll(
            @RequestParam(required = false) Integer cityId,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.success(runnerService.query(cityId, gender, keyword, page, size));
    }

    @GetMapping("/me")
    public ApiResponse<Runner> getMe() {
        String email = AuthContext.getEmail();
        if (email == null) {
            return new ApiResponse<>(401, "Unauthorized", null);
        }
        Runner runner = runnerService.getByEmail(email);
        if (runner == null) {
            return new ApiResponse<>(404, "Runner not found", null);
        }
        return ApiResponse.success(runner);
    }
}
