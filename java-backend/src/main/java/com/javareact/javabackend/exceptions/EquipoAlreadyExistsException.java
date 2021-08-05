package com.javareact.javabackend.exceptions;

public class EquipoAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EquipoAlreadyExistsException(String message) {
        super(message);
    }
    
}
