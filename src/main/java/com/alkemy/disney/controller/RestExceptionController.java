package com.alkemy.disney.controller;

import com.alkemy.disney.dto.Exceptions.ExceptionDTO;
import com.alkemy.disney.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.ParseException;
import java.util.Arrays;
import java.util.stream.Collectors;


@ControllerAdvice
@Component
public class RestExceptionController extends ResponseEntityExceptionHandler {

    private ObjectError o;

    /*
    *   Todos los tipos de excepciones (Manejador general)
     */
    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> handleAllUncaughtException(Throwable ex, WebRequest request){
        ExceptionDTO exceptionDTO = new ExceptionDTO(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ErrorMessages.INTERNAL_SERVER_ERROR,
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

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(
                HttpStatus.BAD_REQUEST,
                ErrorMessages.BAD_REQUEST,
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

    /*
     * Error en la validacion de parametros
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(
                HttpStatus.BAD_REQUEST,
                ErrorMessages.ERROR_VALIDATION,
                ex.getBindingResult().getFieldErrors()
                        .stream().map( o -> {
                            String message = o.getDefaultMessage();
                            message = (message!=null&&message.contains("%s"))?
                                    String.format(message, o.getField()) : message;
                            return message;
                        })
                        .collect(Collectors.toList())
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

    @ExceptionHandler(value = {NotFound.class})
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request){
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

    @ExceptionHandler(value = {NotValid.class})
    protected ResponseEntity<Object> handleNotValid(RuntimeException ex, WebRequest request){
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

    @ExceptionHandler(value = {ResponseError.class})
    protected ResponseEntity<Object> handleResponseError(RuntimeException ex, WebRequest request){
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

    @ExceptionHandler(value = {ParseException.class})
    protected ResponseEntity<Object> handleParseException(RuntimeException ex, WebRequest request){
        ExceptionDTO exceptionDTO = new ExceptionDTO(
                HttpStatus.BAD_REQUEST,
                ErrorMessages.ERROR_DATE,
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
