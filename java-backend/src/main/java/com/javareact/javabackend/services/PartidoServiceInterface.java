package com.javareact.javabackend.services;

import java.util.List;

import com.javareact.javabackend.shared.dtos.PartidoDto;

public interface PartidoServiceInterface {
    
    public PartidoDto createPartido(PartidoDto partidoDto, String correo);
    public List<PartidoDto> getPartidos(short limit, short offset);
    public PartidoDto updatePartido(long id, PartidoDto partidoDto);

}
