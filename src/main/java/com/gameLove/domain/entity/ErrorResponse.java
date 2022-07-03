package com.gameLove.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class ErrorResponse {
    @JsonProperty
    Integer statusCode;
    @JsonProperty
    HttpStatus status;
    @JsonProperty
    String message;
}
