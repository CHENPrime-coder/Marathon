package com.example.marathon.service;

import com.example.marathon.dto.result.RaceResultResponse;
import com.example.marathon.mapper.RaceResultMapper;
import com.example.marathon.pojo.ResultStatus;
import com.example.marathon.dao.RaceResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaceResultService {
    private final RaceResultMapper mapper;

    public RaceResultService(RaceResultMapper mapper) {
        this.mapper = mapper;
    }

    public List<RaceResultResponse> query(Integer competitionId, String gender) {
        List<RaceResult> results = mapper.query(competitionId, gender);
        return results.stream()
                .map(r -> new RaceResultResponse(
                        r.getResultId(),
                        r.getStatus(),
                        ResultStatus.getResultStatus(r.getStatus()),
                        r.getCompletionTime(),
                        r.getCompetitionId(),
                        r.getRunnerEmail(),
                        r.getRunnerAvatar(),
                        r.getRunnerCity(),
                        r.getRunnerExperienceLevel()))
                .collect(Collectors.toList());
    }

    public RaceResultResponse getById(Integer resultId) {
        RaceResult r = mapper.findById(resultId);
        if (r == null) {
            return null;
        }
        return new RaceResultResponse(
                r.getResultId(),
                r.getStatus(),
                ResultStatus.getResultStatus(r.getStatus()),
                r.getCompletionTime(),
                r.getCompetitionId(),
                r.getRunnerEmail(),
                r.getRunnerAvatar(),
                r.getRunnerCity(),
                r.getRunnerExperienceLevel());
    }
}
