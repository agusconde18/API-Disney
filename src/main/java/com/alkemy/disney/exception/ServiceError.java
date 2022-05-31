package com.alkemy.disney.exception;

public class ServiceError extends RuntimeException{
    public ServiceError(String msn) {
        super(msn);
    }
}
