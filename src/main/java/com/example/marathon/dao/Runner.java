package com.example.marathon.dao;

import java.time.LocalDate;
import com.example.marathon.pojo.Gender;
import lombok.Data;

@Data
public class Runner {
    private String email;
    private String name;
    private Gender gender;
    private LocalDate dateOfBirth;
    private Integer cityId;
    private String experience;
    private String photo;
}
