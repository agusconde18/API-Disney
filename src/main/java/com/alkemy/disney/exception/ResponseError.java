package com.alkemy.disney.exception;

public class ResponseError extends RuntimeException{
    public ResponseError(String msn) {
        super(msn);
    }
}
