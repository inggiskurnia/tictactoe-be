package com.fwd.fwd.common.exceptions;

import com.fwd.fwd.common.response.ApiResponse;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<?> handleGameNotFoundException(GameNotFoundException ex){
        return ApiResponse.failedResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(GameAlreadyFinishedException.class)
    public ResponseEntity<?> handleGameAlreadyFinishedException(GameAlreadyFinishedException ex){
        return ApiResponse.failedResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
}
