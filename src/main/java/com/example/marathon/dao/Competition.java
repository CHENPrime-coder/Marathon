package com.example.marathon.dao;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class Competition {
    private Integer id;
    private String name;
    private BigDecimal regCost;
}
