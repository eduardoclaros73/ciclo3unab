package com.javareact.javabackend.shared.dtos;

import java.io.Serializable;

public class EquipoDto implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private long id;
    private String nombre;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
