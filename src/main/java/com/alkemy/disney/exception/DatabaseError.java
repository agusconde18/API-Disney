package com.alkemy.disney.exception;

public class DatabaseError extends RuntimeException{
    public DatabaseError(String msn) {
        super(msn);
    }
}
