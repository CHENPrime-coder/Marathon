package com.example.marathon.service;

import com.example.marathon.mapper.CityMapper;
import com.example.marathon.dao.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final CityMapper cityMapper;

    public CityService(CityMapper cityMapper) {
        this.cityMapper = cityMapper;
    }

    public List<City> list() {
        return cityMapper.findAll();
    }
}
