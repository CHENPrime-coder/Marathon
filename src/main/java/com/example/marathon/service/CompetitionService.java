package com.example.marathon.service;

import com.example.marathon.dao.RaceKitOption;
import com.example.marathon.exception.BizException;
import com.example.marathon.mapper.CompetitionMapper;
import com.example.marathon.dao.Competition;
import com.example.marathon.mapper.RaceKitOptionMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CompetitionService {
    private final CompetitionMapper competitionMapper;
    private final RaceKitOptionMapper raceKitOptionMapper;

    public CompetitionService(CompetitionMapper competitionMapper, RaceKitOptionMapper raceKitOptionMapper) {
        this.competitionMapper = competitionMapper;
        this.raceKitOptionMapper = raceKitOptionMapper;
    }

    public List<Competition> list() {
        return competitionMapper.findAll();
    }

    public BigDecimal calculatePrice(Integer cid, Integer oid) {
        Competition competition = competitionMapper.findById(cid);
        if (competition == null) {
            throw new BizException(400, "Competition not found");
        }
        RaceKitOption raceKitOption = raceKitOptionMapper.findById(oid);
        if (raceKitOption == null) {
            throw new BizException(400, "Race kit option not found");
        }
        return competition.getRegCost().add(raceKitOption.getCost());
    }
}
