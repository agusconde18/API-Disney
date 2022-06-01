package com.alkemy.disney.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.ParseException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DateFormatException extends ParseException {
    public DateFormatException(String msn, int errorOffset) {
        super(msn, errorOffset);
    }
}
