package com.example.marathon.service;

import com.example.marathon.api.PageResponse;
import com.example.marathon.dto.common.ImportResult;
import com.example.marathon.dto.volunteer.VolunteerRequest;
import com.example.marathon.dto.volunteer.VolunteerResponse;
import com.example.marathon.mapper.CityMapper;
import com.example.marathon.mapper.VolunteerMapper;
import com.example.marathon.pojo.Gender;
import com.example.marathon.dao.Volunteer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VolunteerService {

    private final VolunteerMapper mapper;
    private final CityMapper cityMapper;

    public VolunteerService(VolunteerMapper mapper, CityMapper cityMapper) {
        this.mapper = mapper;
        this.cityMapper = cityMapper;
    }

    public PageResponse<VolunteerResponse> query(Integer cityId, String gender, String keyword,
            int page, int size) {
        if (page < 1) {
            page = 1;
        }
        long offset = (long) (page - 1) * size;
        long total = mapper.count(cityId, gender, keyword);
        List<VolunteerResponse> list = mapper.query(cityId, gender, keyword, offset, size);
        return new PageResponse<>(total, list);
    }

    @Transactional
    public Volunteer save(VolunteerRequest request) {
        Volunteer volunteer = new Volunteer();
        volunteer.setId(request.getId());
        volunteer.setName(request.getName());
        volunteer.setCityId(request.getCityId());
        volunteer.setDateOfBirth(request.getDateOfBirth());
        volunteer.setGender(Gender.valueOf(request.getGender().toUpperCase()));
        mapper.insert(volunteer);
        return volunteer;
    }

    @Transactional
    public ImportResult importCsv(MultipartFile file) {
        int success = 0;
        int fail = 0;
        List<Integer> failedLines = new ArrayList<>();
        List<Volunteer> volunteers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            // skip header
            reader.readLine();

            String line;
            int lineNum = 1; // Header is line 1, data starts at 2
            while ((line = reader.readLine()) != null) {
                lineNum++;
                if (line.trim().isEmpty()) {
                    continue;
                }
                try {
                    String[] parts = line.split(",");
                    if (parts.length < 4) {
                        throw new IllegalArgumentException("Invalid format");
                    }
                    Volunteer v = new Volunteer();
                    v.setName(parts[0].trim());
                    v.setCityId(resolveCityId(parts[1].trim()));
                    v.setDateOfBirth(LocalDate.parse(parts[2].trim()));
                    v.setGender(Gender.valueOf(parts[3].trim().toUpperCase()));
                    volunteers.add(v);
                } catch (Exception e) {
                    fail++;
                    failedLines.add(lineNum);
                }
            }
            if (!volunteers.isEmpty()) {
                mapper.insertBatch(volunteers);
                success = volunteers.size();
            }
            return new ImportResult(success, fail, failedLines);
        } catch (Exception e) {
            throw new RuntimeException("导入失败", e);
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
