package com.gameLove.domain.exception;

import com.gameLove.domain.Error.ApiError;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * base exception class
 * override when you create original exception class
 */
abstract class BaseException extends RuntimeException{
    HttpStatus status;
    String message;

    public BaseException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ApiError createApiError(HttpServletRequest request) {
        return new  ApiError(status, message);
    }
}
