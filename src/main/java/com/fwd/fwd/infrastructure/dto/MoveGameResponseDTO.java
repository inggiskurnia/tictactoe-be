package com.fwd.fwd.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoveGameResponseDTO {

    private UUID gameId;
    private String[][] board;
}
