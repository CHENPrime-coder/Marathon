package com.example.marathon.dto.competition;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CompetitionRequest {
    private Integer id;
    @NotBlank
    private String name;
    @NotNull
    private BigDecimal regCost;
}
