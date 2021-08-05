package com.javareact.javabackend.exceptions;

public class CorreoAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CorreoAlreadyExistsException(String message) {
        super(message);
    }
    
}
