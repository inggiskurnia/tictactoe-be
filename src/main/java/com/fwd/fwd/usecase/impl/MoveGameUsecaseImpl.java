package com.fwd.fwd.usecase.impl;

import com.fwd.fwd.common.enums.Mark;
import com.fwd.fwd.common.enums.Status;
import com.fwd.fwd.common.exceptions.GameAlreadyFinishedException;
import com.fwd.fwd.common.exceptions.GameNotFoundException;
import com.fwd.fwd.common.utils.MiniMax;
import com.fwd.fwd.entity.Game;
import com.fwd.fwd.infrastructure.dto.MoveGameRequestDTO;
import com.fwd.fwd.infrastructure.dto.MoveGameResponseDTO;
import com.fwd.fwd.infrastructure.repository.GameRepository;
import com.fwd.fwd.usecase.MoveGameUsecase;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MoveGameUsecaseImpl implements MoveGameUsecase {

    private final GameRepository gameRepository;

    public MoveGameUsecaseImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    @Transactional
    public MoveGameResponseDTO moveGame(MoveGameRequestDTO req) {

        Game game = gameRepository.findByGameId(req.getGameId())
                .orElseThrow(()-> new GameNotFoundException("Game with UUID : " + req.getGameId() + " not found !"));

        if (!game.getStatus().equals(Status.IN_PROGRESS.getValue())){
            throw new GameAlreadyFinishedException("Game already finished");
        }

        // human move
        game.setMarkOnBoard(req.getRow(), req.getCol(), false);

        // evaluate
        if (game.evaluateBoard(false)){
            game.setStatus(game.getHumanMark().equals(Mark.X.getValue()) ? Status.X_WON.getValue() : Status.O_WON.getValue());
            return new MoveGameResponseDTO(game.getGameId(), game.getBoard(), game.getStatus());
        }
        if (game.getAvailableMoves().isEmpty()){
            game.setStatus(Status.DRAW.getValue());
            return new MoveGameResponseDTO(game.getGameId(), game.getBoard(), game.getStatus());
        }

        // ai move
        int[] aiMove = MiniMax.getBestMove(game);
        game.setMarkOnBoard(aiMove[0], aiMove[1], true);

        // evaluate
        if (game.evaluateBoard(true)){
            game.setStatus(game.getAiMark().equals(Mark.X.getValue()) ? Status.X_WON.getValue() : Status.O_WON.getValue());
            return new MoveGameResponseDTO(game.getGameId(), game.getBoard(), game.getStatus());
        }

        if (game.getAvailableMoves().isEmpty()){
            game.setStatus(Status.DRAW.getValue());
            return new MoveGameResponseDTO(game.getGameId(), game.getBoard(), game.getStatus());
        }

        gameRepository.save(game);


        return new MoveGameResponseDTO(game.getGameId(), game.getBoard(), game.getStatus());
    }
}
