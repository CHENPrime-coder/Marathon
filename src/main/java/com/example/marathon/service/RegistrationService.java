package com.example.marathon.service;

import com.example.marathon.dto.registration.RegistrationRequest;
import com.example.marathon.dto.registration.RegistrationResponse;
import com.example.marathon.exception.BizException;
import com.example.marathon.mapper.CompetitionMapper;
import com.example.marathon.mapper.RaceKitOptionMapper;
import com.example.marathon.mapper.RegistrationMapper;
import com.example.marathon.table.Competition;
import com.example.marathon.table.RaceKitOption;
import com.example.marathon.table.Registration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RegistrationService {
    private final RegistrationMapper registrationMapper;
    private final CompetitionMapper competitionMapper;
    private final RaceKitOptionMapper raceKitOptionMapper;

    public RegistrationService(RegistrationMapper registrationMapper,
                               CompetitionMapper competitionMapper,
                               RaceKitOptionMapper raceKitOptionMapper) {
        this.registrationMapper = registrationMapper;
        this.competitionMapper = competitionMapper;
        this.raceKitOptionMapper = raceKitOptionMapper;
    }

    @Transactional
    public RegistrationResponse register(RegistrationRequest request) {
        Registration existing = registrationMapper.findOne(request.getEmail(), request.getCompetitionId());
        if (existing != null) {
            throw new BizException(400, "already registered");
        }
        Competition competition = competitionMapper.findById(request.getCompetitionId());
        RaceKitOption option = raceKitOptionMapper.findById(request.getOptionId());
        if (competition == null || option == null) {
            throw new BizException(400, "competition or option not found");
        }
        BigDecimal total = competition.getRegCost().add(option.getCost());
        Registration registration = new Registration();
        registration.setEmail(request.getEmail());
        registration.setCompetitionId(request.getCompetitionId());
        registration.setOptionId(request.getOptionId());
        registration.setTotalPrice(total);
        registrationMapper.insert(registration);
        return new RegistrationResponse(total);
    }

    public List<Registration> query(String email, Integer competitionId) {
        return registrationMapper.query(email, competitionId);
    }
}
