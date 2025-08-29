package com.fwd.fwd.common.response;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
public class ApiResponse<T> {
    private int statusCode;
    private String message;
    private boolean success;
    private T data;

    public ApiResponse(int statusCode, String message){
        this.statusCode = statusCode;
        this.message = message;

        if(statusCode == HttpStatus.OK.value()){
            this.success = true;
        }
    }

    public static <T> ResponseEntity<ApiResponse<T>> failedResponse(int statusCode, String message, T data){
        ApiResponse<T> apiResponse = new ApiResponse<>(statusCode, message);
        apiResponse.setSuccess(false);
        apiResponse.setData(data);
        return ResponseEntity.status(statusCode).body(apiResponse);
    }

    public static <T> ResponseEntity<ApiResponse<T>> failedResponse(int statusCode, String message){
        return ApiResponse.failedResponse(statusCode, message, null);
    }

    public static <T> ResponseEntity<ApiResponse<T>> successfulResponse(int statusCode, String message, T data){
        ApiResponse<T> apiResponse = new ApiResponse<>(statusCode, message);
        apiResponse.setSuccess(true);
        apiResponse.setData(data);
        return ResponseEntity.status(statusCode).body(apiResponse);
    }

    public static <T> ResponseEntity<ApiResponse<T>> successfulResponse(int statusCode, String message){
        return ApiResponse.successfulResponse(statusCode, message, null);
    }


}
