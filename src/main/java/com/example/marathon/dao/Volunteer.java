package com.example.marathon.dao;

import java.time.LocalDate;
import com.example.marathon.pojo.Gender;
import lombok.Data;

@Data
public class Volunteer {
    private Integer id;
    private String name;
    private Integer cityId;
    private LocalDate dateOfBirth;
    private Gender gender;
}
