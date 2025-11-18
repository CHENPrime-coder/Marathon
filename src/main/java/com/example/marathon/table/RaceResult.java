package com.example.marathon.table;

import lombok.Data;

@Data
public class RaceResult {
    private Integer resultId;
    private Integer status;
    private Integer completionTime;
    private Integer competitionId;
    private String runnerEmail;
}
