package com.gameLove.domain;

import com.gameLove.domain.entity.ErrorResponse;
import com.gameLove.domain.exception.NotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * handle original exception
 */
@RestControllerAdvice
public class CustomRestExceptionHandler  {

    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleNotFoundException(
            HttpServletRequest request,
            NotFoundException exception
    ) {
        // you can get request information from HttpServletRequest if needed
        return exception.createApiError(request).toError();
    }
}
