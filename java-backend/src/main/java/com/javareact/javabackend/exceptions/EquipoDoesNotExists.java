package com.javareact.javabackend.exceptions;

public class EquipoDoesNotExists extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EquipoDoesNotExists(String message) {
        super(message);
    }
    
}
