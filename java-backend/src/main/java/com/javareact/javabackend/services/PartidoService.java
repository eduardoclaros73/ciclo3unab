package com.javareact.javabackend.services;

import java.util.ArrayList;
import java.util.List;

import com.javareact.javabackend.entities.EquipoEntity;
import com.javareact.javabackend.entities.PartidoEntity;
import com.javareact.javabackend.entities.UsuarioEntity;
import com.javareact.javabackend.exceptions.EquipoDoesNotExists;
import com.javareact.javabackend.repositories.EquipoRepository;
import com.javareact.javabackend.repositories.PartidoRepository;
import com.javareact.javabackend.repositories.UsuarioRepository;
import com.javareact.javabackend.shared.dtos.PartidoDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidoService implements PartidoServiceInterface {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PartidoRepository partidoRepository;

    @Autowired
    EquipoRepository equipoRepository;

    @Autowired
    ModelMapper modelMapper;

    public PartidoDto createPartido(PartidoDto partidoDto, String correo) {
        PartidoEntity partidoEntity = new PartidoEntity();

        partidoEntity.setFecha(partidoDto.getFecha());
        partidoEntity.setGolesLocal(partidoDto.getGolesLocal());
        partidoEntity.setGolesVisitante(partidoDto.getGolesVisitante());

        UsuarioEntity usuarioEntity = usuarioRepository.findByCorreo(correo);
        partidoEntity.setUsuario(usuarioEntity);

        EquipoEntity localEntity = equipoRepository.findById(partidoDto.getLocal()).orElse(null);
        EquipoEntity visitanteEntity = equipoRepository.findById(partidoDto.getVisitante()).orElse(null);

        if (localEntity == null) {
            throw new EquipoDoesNotExists("Equipo local no existe");
        }
        if (visitanteEntity == null) {
            throw new EquipoDoesNotExists("Equipo visitante no existe");
        }

        partidoEntity.setLocal(localEntity);
        partidoEntity.setVisitante(visitanteEntity);

        PartidoEntity createdPartidoEntity = partidoRepository.save(partidoEntity);
        PartidoDto createdPartidoDto = new PartidoDto();

        createdPartidoDto.setId(createdPartidoEntity.getId());
        createdPartidoDto.setLocal(createdPartidoEntity.getLocal().getId());
        createdPartidoDto.setVisitante(createdPartidoEntity.getVisitante().getId());
        createdPartidoDto.setFecha(createdPartidoEntity.getFecha());
        createdPartidoDto.setGolesLocal(createdPartidoEntity.getGolesLocal());
        createdPartidoDto.setGolesVisitante(createdPartidoEntity.getGolesVisitante());

        return createdPartidoDto;
    }

    public List<PartidoDto> getPartidos(short limit, short offset) {
        List<PartidoEntity> partidoEntities = partidoRepository.getPartidos(
            limit,
            offset
        );
        List<PartidoDto> partidoDtos = new ArrayList<>();

        for (PartidoEntity partidoEntity : partidoEntities) {
            PartidoDto partidoDto = new PartidoDto();

            partidoDto.setId(partidoEntity.getId());
            partidoDto.setLocal(partidoEntity.getLocal().getId());
            partidoDto.setVisitante(partidoEntity.getVisitante().getId());
            partidoDto.setFecha(partidoEntity.getFecha());
            partidoDto.setGolesLocal(partidoEntity.getGolesLocal());
            partidoDto.setGolesVisitante(partidoEntity.getGolesVisitante());

            partidoDtos.add(partidoDto);
        }

        return partidoDtos;
    }

    public PartidoDto updatePartido(long id, PartidoDto partidoDto) {
        PartidoEntity partidoEntity = partidoRepository.findById(id).orElse(null);

        if (partidoEntity == null) {
            throw new EquipoDoesNotExists("Partido no existe");
        }

        partidoEntity.setGolesLocal(partidoDto.getGolesLocal());
        partidoEntity.setGolesVisitante(partidoDto.getGolesVisitante());

        PartidoEntity updatedPartidoEntity = partidoRepository.save(partidoEntity);
        PartidoDto updatedPartidoDto = new PartidoDto();

        updatedPartidoDto.setId(updatedPartidoEntity.getId());
        updatedPartidoDto.setLocal(updatedPartidoEntity.getLocal().getId());
        updatedPartidoDto.setVisitante(updatedPartidoEntity.getVisitante().getId());
        updatedPartidoDto.setFecha(updatedPartidoEntity.getFecha());
        updatedPartidoDto.setGolesLocal(updatedPartidoEntity.getGolesLocal());
        updatedPartidoDto.setGolesVisitante(updatedPartidoEntity.getGolesVisitante());

        return updatedPartidoDto;
    }
    
}
