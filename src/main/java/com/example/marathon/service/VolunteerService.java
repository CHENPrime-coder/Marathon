package com.example.marathon.service;

import com.example.marathon.dto.volunteer.VolunteerRequest;
import com.example.marathon.mapper.CityMapper;
import com.example.marathon.mapper.VolunteerMapper;
import com.example.marathon.pojo.Gender;
import com.example.marathon.table.Volunteer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

    @Service
    public class VolunteerService {
        private final VolunteerMapper mapper;
        private final CityMapper cityMapper;

        public VolunteerService(VolunteerMapper mapper, CityMapper cityMapper) {
            this.mapper = mapper;
            this.cityMapper = cityMapper;
        }

    public List<Volunteer> query(Integer cityId, String gender, String keyword) {
        return mapper.query(cityId, gender, keyword);
    }

    @Transactional
    public Volunteer save(VolunteerRequest request) {
        Volunteer volunteer = new Volunteer();
        volunteer.setId(request.getId());
        volunteer.setName(request.getName());
        volunteer.setCityId(request.getCityId());
        volunteer.setDateOfBirth(request.getDateOfBirth());
        volunteer.setGender(Gender.valueOf(request.getGender()));
        mapper.insert(volunteer);
        return volunteer;
    }

    @Transactional
    public int importCsv(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            return reader.lines()
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .map(line -> line.split(","))
                    .filter(parts -> parts.length >= 4)
                    .map(parts -> {
                        Volunteer v = new Volunteer();
                        v.setName(parts[0].trim());
                        v.setCityId(resolveCityId(parts[1].trim()));
                        v.setDateOfBirth(java.time.LocalDate.parse(parts[2].trim()));
                        v.setGender(Gender.valueOf(parts[3].trim()));
                        return v;
                    })
                    .peek(mapper::insert)
                    .collect(Collectors.toList())
                    .size();
        } catch (Exception e) {
            throw new RuntimeException("failed to import volunteers csv", e);
        }
    }

    private Integer resolveCityId(String cityName) {
        try {
            return Integer.parseInt(cityName);
        } catch (NumberFormatException ignore) {
            Integer id = cityMapper.findIdByName(cityName);
            if (id == null) {
                throw new RuntimeException("unknown city: " + cityName);
            }
            return id;
        }
    }
}
