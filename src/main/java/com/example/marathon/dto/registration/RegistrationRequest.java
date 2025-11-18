package com.example.marathon.dto.registration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegistrationRequest {
    @Email
    private String email;
    @NotNull
    private Integer optionId;
    @NotNull
    private Integer competitionId;
}
