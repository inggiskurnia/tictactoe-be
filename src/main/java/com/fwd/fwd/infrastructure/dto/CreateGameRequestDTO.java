package com.fwd.fwd.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGameRequestDTO {
    private int colSize;
    private int rowSize;
    private int winLength;
    private String humanMark;
}
