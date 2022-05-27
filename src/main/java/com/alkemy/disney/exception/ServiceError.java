package com.alkemy.disney.exception;

public class ServiceError extends Exception{
    public ServiceError(String msn) {
        super(msn);
    }
}
