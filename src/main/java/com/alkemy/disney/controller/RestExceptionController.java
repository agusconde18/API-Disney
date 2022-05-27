package com.alkemy.disney.controller;

import com.alkemy.disney.dto.Exceptions.ExceptionDTO;
import com.alkemy.disney.exception.DatabaseError;
import com.alkemy.disney.exception.ServiceError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;


@ControllerAdvice
public class RestExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Throwable.class})
    protected ResponseEntity<Object> handleThrowable(Throwable ex, WebRequest request){
        ExceptionDTO exceptionDTO = new ExceptionDTO(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                Arrays.asList("")
        );

        return handleExceptionInternal(
                (Exception) ex,
                exceptionDTO,
                new HttpHeaders(),
                exceptionDTO.getStatus(),
                request
        );
    }

    @ExceptionHandler(value = {DatabaseError.class})
    protected ResponseEntity<Object> handleDatabaseError(Throwable ex, WebRequest request){
        ExceptionDTO exceptionDTO = new ExceptionDTO(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                Arrays.asList("")
        );

        return handleExceptionInternal(
                (Exception) ex,
                exceptionDTO,
                new HttpHeaders(),
                exceptionDTO.getStatus(),
                request
        );
    }

    @ExceptionHandler(value = {ServiceError.class})
    protected ResponseEntity<Object> handleServiceError(RuntimeException ex, WebRequest request){
        ExceptionDTO exceptionDTO = new ExceptionDTO(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                Arrays.asList("")
        );

        return handleExceptionInternal(
                (Exception) ex,
                exceptionDTO,
                new HttpHeaders(),
                exceptionDTO.getStatus(),
                request
        );
    }

}
