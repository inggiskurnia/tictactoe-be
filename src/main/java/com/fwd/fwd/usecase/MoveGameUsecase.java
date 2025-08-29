package com.fwd.fwd.usecase;

import com.fwd.fwd.infrastructure.dto.MoveGameRequestDTO;
import com.fwd.fwd.infrastructure.dto.MoveGameResponseDTO;

public interface MoveGameUsecase {
    MoveGameResponseDTO moveGame(MoveGameRequestDTO req);
}
