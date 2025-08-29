package com.fwd.fwd.usecase.impl;

import com.fwd.fwd.common.utils.MiniMax;
import com.fwd.fwd.entity.Game;
import com.fwd.fwd.infrastructure.dto.MoveGameRequestDTO;
import com.fwd.fwd.infrastructure.dto.MoveGameResponseDTO;
import com.fwd.fwd.infrastructure.repository.GameRepository;
import com.fwd.fwd.usecase.MoveGameUsecase;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class MoveGameUsecaseImpl implements MoveGameUsecase {

    private final GameRepository gameRepository;

    public MoveGameUsecaseImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public MoveGameResponseDTO moveGame(MoveGameRequestDTO req) {

        Game game = gameRepository.findByGameId(req.getGameId())
                .orElseThrow(()-> new NoSuchElementException("Game with UUID : " + req.getGameId() + " not found !"));

        game.setMarkOnBoard(req.getRow(), req.getCol(), false);

        int[] aiMove = MiniMax.getBestMove(game);
        game.setMarkOnBoard(aiMove[0], aiMove[1], true);

        return new MoveGameResponseDTO(game.getGameId(), game.getBoard());
    }
}
