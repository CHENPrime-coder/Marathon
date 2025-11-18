package com.example.marathon.service;

import com.example.marathon.mapper.CompetitionMapper;
import com.example.marathon.table.Competition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionService {
    private final CompetitionMapper competitionMapper;

    public CompetitionService(CompetitionMapper competitionMapper) {
        this.competitionMapper = competitionMapper;
    }

    public List<Competition> list() {
        return competitionMapper.findAll();
    }

}
