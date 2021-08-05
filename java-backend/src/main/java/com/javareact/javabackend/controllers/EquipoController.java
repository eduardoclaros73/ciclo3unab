package com.javareact.javabackend.controllers;

import java.util.ArrayList;
import java.util.List;

import com.javareact.javabackend.models.requests.EquipoCreateRequestModel;
import com.javareact.javabackend.models.responses.EquipoDetailsResponseModel;
import com.javareact.javabackend.services.EquipoServiceInterface;
import com.javareact.javabackend.shared.dtos.EquipoDto;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equipos")
public class EquipoController {

    @Autowired
    EquipoServiceInterface equipoService;

    @PostMapping(produces = {
        MediaType.APPLICATION_XML_VALUE,
        MediaType.APPLICATION_JSON_VALUE
    })
    public EquipoDetailsResponseModel createEquipo(@RequestBody EquipoCreateRequestModel request) {
        EquipoDto equipoDto = new EquipoDto();
        BeanUtils.copyProperties(request, equipoDto);

        EquipoDto createdEquipoDto = equipoService.createEquipo(equipoDto);
        EquipoDetailsResponseModel equipoResponse = new EquipoDetailsResponseModel();

        BeanUtils.copyProperties(createdEquipoDto, equipoResponse);
        
        return equipoResponse;
    }

    @GetMapping(
        produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
        }
    )
    public List<EquipoDetailsResponseModel> getEquipos() {
        List<EquipoDto> equipoDtos = equipoService.getEquipos();
        List<EquipoDetailsResponseModel> equipoResponses = new ArrayList<>();

        for (EquipoDto equipoDto : equipoDtos) {
            EquipoDetailsResponseModel equipoResponse = new EquipoDetailsResponseModel();
            BeanUtils.copyProperties(equipoDto, equipoResponse);
            equipoResponses.add(equipoResponse);
        }

        return equipoResponses;
    }

}
