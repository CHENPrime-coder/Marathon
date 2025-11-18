package com.example.marathon.dto.runner;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RunnerUpdateRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String gender;
    @NotNull
    private java.time.LocalDate dateOfBirth;
    @NotNull
    private Integer cityId;
    @NotBlank
    private String experience;
}
