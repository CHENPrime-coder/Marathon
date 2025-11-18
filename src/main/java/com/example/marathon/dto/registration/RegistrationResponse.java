package com.example.marathon.dto.registration;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationResponse {
    private BigDecimal totalPrice;
}
