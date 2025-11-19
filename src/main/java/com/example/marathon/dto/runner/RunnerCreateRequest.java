package com.example.marathon.dto.runner;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RunnerCreateRequest {
    @Email
    @NotBlank
    private String email;
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
    @NotBlank
    private String password;
    @NotBlank
    private String photo;
}
