package com.example.marathon.service;

import com.example.marathon.pojo.Gender;
import org.springframework.stereotype.Service;

@Service
public class GenderService {

    public String getGenderLabel(String value) {
        return switch (value.toLowerCase()) {
            case "male" -> "男性";
            case "female" -> "女性";
            default -> "未知性别";
        };
    }

    public Gender getGender(String label) {
        if (label.equals("男性")) {
            return Gender.MALE;
        } else {
            return Gender.FEMALE;
        }
    }
}
