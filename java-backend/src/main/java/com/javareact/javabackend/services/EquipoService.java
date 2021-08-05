package com.javareact.javabackend.services;

import java.util.ArrayList;
import java.util.List;

import com.javareact.javabackend.entities.EquipoEntity;
import com.javareact.javabackend.exceptions.EquipoAlreadyExistsException;
import com.javareact.javabackend.repositories.EquipoRepository;
import com.javareact.javabackend.shared.dtos.EquipoDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipoService implements EquipoServiceInterface {

    @Autowired
    EquipoRepository equipoRepository;

    @Autowired
    ModelMapper modelMapper;

    public EquipoDto createEquipo(EquipoDto equipoDto) {
        if (equipoRepository.findByNombre(equipoDto.getNombre()) != null) {
            throw new EquipoAlreadyExistsException("Equipo ya existe");
        }

        EquipoEntity equipoEntity = modelMapper.map(equipoDto, EquipoEntity.class);

        EquipoEntity createdEquipoEntity = equipoRepository.save(equipoEntity);

        EquipoDto createdEquipoDto = modelMapper.map(createdEquipoEntity, EquipoDto.class);

        return createdEquipoDto;
    }

    @Override
    public List<EquipoDto> getEquipos() {
        List<EquipoEntity> equipoEntities = equipoRepository.getEquipos();
        List<EquipoDto> equipoDtos = new ArrayList<>();

        for (EquipoEntity equipoEntity : equipoEntities) {
            EquipoDto equipoDto = modelMapper.map(equipoEntity, EquipoDto.class);
            equipoDtos.add(equipoDto);
        }

        return equipoDtos;
    }
    
}
