package com.example.marathon.dao;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class RaceKitOption {
    private Integer optionId;
    private String option;
    private BigDecimal cost;
}
