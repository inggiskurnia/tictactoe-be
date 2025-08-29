package com.fwd.fwd.usecase;

import com.fwd.fwd.infrastructure.dto.CreateGameRequestDTO;
import com.fwd.fwd.infrastructure.dto.CreateGameResponseDTO;

public interface CreateGameUsecase {

    CreateGameResponseDTO createGame(CreateGameRequestDTO req);

}
