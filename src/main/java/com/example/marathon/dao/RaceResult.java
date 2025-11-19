package com.example.marathon.dao;

import lombok.Data;

@Data
public class RaceResult {
    private Integer resultId;
    private Integer status;
    private Integer completionTime;
    private Integer competitionId;
    private String runnerEmail;
    private String runnerAvatar;
    private String runnerCity;
    private String runnerExperienceLevel;
}
