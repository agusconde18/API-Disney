package com.alkemy.disney.Exceptions;

public class ServiceError extends Exception{
    public ServiceError(String msn) {
        super(msn);
    }
}
