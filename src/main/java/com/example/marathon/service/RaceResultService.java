package com.example.marathon.service;

import com.example.marathon.dto.result.RaceResultResponse;
import com.example.marathon.mapper.RaceResultMapper;
import com.example.marathon.pojo.ResultStatus;
import com.example.marathon.table.RaceResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaceResultService {
    private final RaceResultMapper mapper;

    public RaceResultService(RaceResultMapper mapper) {
        this.mapper = mapper;
    }

    public List<RaceResultResponse> query(Integer competitionId, String runnerEmail, Integer status, String runnerName) {
        List<RaceResult> results = mapper.query(competitionId, runnerEmail, status, runnerName);
        return results.stream()
                .map(r -> new RaceResultResponse(
                        r.getResultId(),
                        r.getStatus(),
                        ResultStatus.getResultStatus(r.getStatus()),
                        r.getCompletionTime(),
                        r.getCompetitionId(),
                        r.getRunnerEmail()
                ))
                .collect(Collectors.toList());
    }
}
