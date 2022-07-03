package com.gameLove.domain.exception;

import org.springframework.http.HttpStatus;

/**
 * Original Exception class for Not FoundException
 */
public class NotFoundException extends  BaseException {
     public NotFoundException(String message) {
         super(HttpStatus.BAD_REQUEST,message);
     }
}