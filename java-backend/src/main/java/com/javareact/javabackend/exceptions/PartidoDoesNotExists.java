package com.javareact.javabackend.exceptions;

public class PartidoDoesNotExists extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PartidoDoesNotExists(String message) {
        super(message);
    }
    
}
