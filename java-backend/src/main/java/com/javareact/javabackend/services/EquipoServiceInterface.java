package com.javareact.javabackend.services;

import java.util.List;

import com.javareact.javabackend.shared.dtos.EquipoDto;

public interface EquipoServiceInterface {

    public EquipoDto createEquipo(EquipoDto equipoDto);
    public List<EquipoDto> getEquipos();
    
}
