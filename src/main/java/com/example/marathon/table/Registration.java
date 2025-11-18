package com.example.marathon.table;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class Registration {
    private String email;
    private Integer optionId;
    private Integer competitionId;
    private BigDecimal totalPrice;
}
