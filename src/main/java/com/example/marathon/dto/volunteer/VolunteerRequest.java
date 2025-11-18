package com.example.marathon.dto.volunteer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VolunteerRequest {
    private Integer id;
    @NotBlank
    private String name;
    @NotNull
    private Integer cityId;
    @NotNull
    private java.time.LocalDate dateOfBirth;
    @NotBlank
    private String gender;
}
