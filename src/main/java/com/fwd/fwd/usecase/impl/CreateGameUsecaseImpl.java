package com.fwd.fwd.usecase.impl;

import com.fwd.fwd.common.enums.Mark;
import com.fwd.fwd.common.enums.Status;
import com.fwd.fwd.entity.Game;
import com.fwd.fwd.infrastructure.dto.CreateGameRequestDTO;
import com.fwd.fwd.infrastructure.dto.CreateGameResponseDTO;
import com.fwd.fwd.infrastructure.repository.GameRepository;
import com.fwd.fwd.usecase.CreateGameUsecase;
import org.springframework.stereotype.Service;

@Service
public class CreateGameUsecaseImpl implements CreateGameUsecase {

    private final GameRepository gameRepository;

    public CreateGameUsecaseImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public CreateGameResponseDTO createGame(CreateGameRequestDTO req) {

        String[][] board = new String[req.getRowSize()][req.getColSize()];
        for (int row = 0; row < req.getRowSize(); row++) {
            for (int col = 0; col < req.getColSize(); col++) {
                board[row][col] = Mark.EMPTY.getValue();
            }
        }

        Game newGame = new Game();
        newGame.setRowSize(req.getRowSize());
        newGame.setColSize(req.getColSize());
        newGame.setWinLength(req.getWinLength());
        newGame.setHumanMark(req.getHumanMark());
        newGame.setAiMark(req.getHumanMark().equals(Mark.X.getValue()) ?
                Mark.O.getValue() : Mark.X.getValue());
        newGame.setStatus(Status.IN_PROGRESS.getValue());
        newGame.setBoard(board);

        Game createdGame = gameRepository.save(newGame);

        return new CreateGameResponseDTO(createdGame.getGameId(), createdGame.getRowSize(), createdGame.getColSize(),
                createdGame.getHumanMark(), createdGame.getStatus(), createdGame.getBoard());
    }
}
