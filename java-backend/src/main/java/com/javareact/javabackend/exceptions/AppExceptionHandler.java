package com.javareact.javabackend.exceptions;

import java.util.Date;

import com.javareact.javabackend.models.responses.ErrorResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AppExceptionHandler {
    
    @ExceptionHandler(value = CorreoAlreadyExistsException.class)
    public ResponseEntity<Object> handleCorreoExistsException(
        CorreoAlreadyExistsException e,
        WebRequest webRequest
    ) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), e.getMessage());
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UsernameAlreadyExistsException.class)
    public ResponseEntity<Object> handleUsernameExistsException(
        UsernameAlreadyExistsException e,
        WebRequest webRequest
    ) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), e.getMessage());
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EquipoAlreadyExistsException.class)
    public ResponseEntity<Object> handleEquipoExistsException(
        EquipoAlreadyExistsException e,
        WebRequest webRequest
    ) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), e.getMessage());
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EquipoDoesNotExists.class)
    public ResponseEntity<Object> handleEquipoDoesNotExistsException(
        EquipoDoesNotExists e,
        WebRequest webRequest
    ) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), e.getMessage());
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = PartidoDoesNotExists.class)
    public ResponseEntity<Object> handlePartidoDoesNotExistsException(
        PartidoDoesNotExists e,
        WebRequest webRequest
    ) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), e.getMessage());
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleException(
        Exception e,
        WebRequest webRequest
    ) {
        ErrorResponse errorResponse = new ErrorResponse(new Date(), e.getMessage());
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
