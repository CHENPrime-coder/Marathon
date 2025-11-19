package com.example.marathon.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportResult {
    private int successCount;
    private int failCount;
    private List<Integer> failedLines;
}
