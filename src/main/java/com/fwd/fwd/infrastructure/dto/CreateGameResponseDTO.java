package com.fwd.fwd.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGameResponseDTO {

    private UUID gameId;
    private int rowSize;
    private int colSize;
    private String humanMark;
    private String status;
    private String[][] board;
}
