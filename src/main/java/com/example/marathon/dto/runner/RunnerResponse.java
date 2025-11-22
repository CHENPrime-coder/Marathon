package com.example.marathon.dto.runner;

import com.example.marathon.pojo.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RunnerResponse {
    private String email;
    private String name;
    private Gender gender;
    private LocalDate dateOfBirth;
    private Integer cityId;
    private String experience;
    private String photo;
    private String cityName;
}
