package com.gameLove.domain.Error;

import com.gameLove.domain.entity.ErrorResponse;
import lombok.Data;
import org.springframework.http.HttpStatus;


public class ApiError {

    private final HttpStatus status;
    private final String message;

    public ApiError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * create error response
     * @return Error Response
     */
    public ErrorResponse toError(){
        ErrorResponse response = new ErrorResponse();
        response.setStatusCode(this.status.value());
        response.setStatus(this.status);
        response.setMessage(this.message);
        return response;
    }
}
