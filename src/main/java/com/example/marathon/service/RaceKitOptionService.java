package com.example.marathon.service;

import com.example.marathon.mapper.RaceKitOptionMapper;
import com.example.marathon.dao.RaceKitOption;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaceKitOptionService {
    private final RaceKitOptionMapper mapper;

    public RaceKitOptionService(RaceKitOptionMapper mapper) {
        this.mapper = mapper;
    }

    public List<RaceKitOption> list() {
        return mapper.findAll();
    }
}
