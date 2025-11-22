package com.example.marathon.dto.volunteer;

import com.example.marathon.pojo.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VolunteerResponse {
    private Integer id;
    private String name;
    private Integer cityId;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String cityName;
}
