package com.alkemy.disney.exception;

public class DatabaseError extends Exception{
    public DatabaseError(String msn) {
        super(ErrorMessages.DATABASE_ERROR);
    }
}
