package com.fwd.fwd.infrastructure.controller;

import com.fwd.fwd.common.response.ApiResponse;
import com.fwd.fwd.infrastructure.dto.CreateGameRequestDTO;
import com.fwd.fwd.infrastructure.dto.MoveGameRequestDTO;
import com.fwd.fwd.usecase.CreateGameUsecase;
import com.fwd.fwd.usecase.MoveGameUsecase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/games")
public class GameController {

    private final CreateGameUsecase createGameUsecase;
    private final MoveGameUsecase moveGameUsecase;

    public GameController(CreateGameUsecase createGameUsecase, MoveGameUsecase moveGameUsecase) {
        this.createGameUsecase = createGameUsecase;
        this.moveGameUsecase = moveGameUsecase;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createGame(@RequestBody CreateGameRequestDTO req){
        return ApiResponse.successfulResponse(HttpStatus.OK.value(), "Successfully create new game !", createGameUsecase.createGame(req));
    }

    @PostMapping("/move")
    public ResponseEntity<?> moveGame(@RequestBody MoveGameRequestDTO req){
        return ApiResponse.successfulResponse(HttpStatus.OK.value(), "Successfully make move !", moveGameUsecase.moveGame(req));
    }
}
