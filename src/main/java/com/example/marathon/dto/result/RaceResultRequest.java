package com.example.marathon.dto.result;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RaceResultRequest {
    private Integer resultId;
    @NotNull
    private Integer status;
    private Integer completionTime;
    @NotNull
    private Integer competitionId;
    @Email
    @NotNull
    private String runnerEmail;
}
