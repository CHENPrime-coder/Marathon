package com.example.marathon.dto.result;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RaceResultResponse {
    private Integer resultId;
    private Integer status;
    private String statusText;
    private Integer completionTime;
    private Integer competitionId;
    private String runnerEmail;
    private String runnerAvatar;
    private String runnerCity;
    private String runnerExperienceLevel;
}
