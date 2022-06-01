package com.alkemy.disney.exception;

public class NotValid extends RuntimeException{
    public NotValid(String msn) {
        super(msn);
    }
}
