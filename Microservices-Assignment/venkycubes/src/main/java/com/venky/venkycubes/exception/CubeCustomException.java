package com.venky.venkycubes.exception;

import lombok.Data;

/**
 * Custom Exception for CubeService related failures
 */
@Data
public class CubeCustomException extends RuntimeException {

    private String errorCode;

    public CubeCustomException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
