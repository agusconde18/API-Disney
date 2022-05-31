package com.alkemy.disney.exception;

public class NotFound extends Exception{
    public NotFound(String msn) {
        super(ErrorMessages.NOT_FOUND_ERROR);
    }
}
