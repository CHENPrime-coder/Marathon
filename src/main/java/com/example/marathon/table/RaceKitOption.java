package com.example.marathon.table;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class RaceKitOption {
    private Integer optionId;
    private String option;
    private BigDecimal cost;
}
