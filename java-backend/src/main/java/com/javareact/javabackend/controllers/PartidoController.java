package com.javareact.javabackend.controllers;

import java.util.ArrayList;
import java.util.List;

import com.javareact.javabackend.models.requests.PartidoCreateRequestModel;
import com.javareact.javabackend.models.requests.PartidoUpdateRequestModel;
import com.javareact.javabackend.models.responses.PartidoDetailsResponseModel;
import com.javareact.javabackend.services.PartidoServiceInterface;
import com.javareact.javabackend.shared.dtos.PartidoDto;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/partidos")
public class PartidoController {

    @Autowired
    PartidoServiceInterface partidoService;

    @PostMapping(produces = {
        MediaType.APPLICATION_XML_VALUE,
        MediaType.APPLICATION_JSON_VALUE
    })
    public PartidoDetailsResponseModel createPartido(@RequestBody PartidoCreateRequestModel request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correo = authentication.getPrincipal().toString();

        PartidoDto partidoDto = new PartidoDto();
        BeanUtils.copyProperties(request, partidoDto);

        PartidoDto createdPartidoDto = partidoService.createPartido(partidoDto, correo);
        PartidoDetailsResponseModel partidoResponse = new PartidoDetailsResponseModel();

        BeanUtils.copyProperties(createdPartidoDto, partidoResponse);
        
        return partidoResponse;
    }

    @GetMapping(
        produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
        }
    )
    public List<PartidoDetailsResponseModel> getPartidos(@RequestParam(defaultValue = "10") short limit, @RequestParam(defaultValue = "0") short offset) {
        List<PartidoDto> partidoDtos = partidoService.getPartidos(limit, offset);
        List<PartidoDetailsResponseModel> partidoResponses = new ArrayList<>();

        for (PartidoDto partidoDto : partidoDtos) {
            PartidoDetailsResponseModel partidoResponse = new PartidoDetailsResponseModel();
            BeanUtils.copyProperties(partidoDto, partidoResponse);
            partidoResponses.add(partidoResponse);
        }

        return partidoResponses;
    }

    @PutMapping(
        produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
        },
        path = "/{id}"
    )
    public PartidoDetailsResponseModel updatePartido(
        @RequestBody PartidoUpdateRequestModel request,
        @PathVariable Long id
    ) {
        PartidoDto partidoDto = new PartidoDto();
        BeanUtils.copyProperties(request, partidoDto);
        
        PartidoDto updatedPartidoDto = partidoService.updatePartido(id, partidoDto);
        PartidoDetailsResponseModel partidoResponse = new PartidoDetailsResponseModel();

        BeanUtils.copyProperties(updatedPartidoDto, partidoResponse);

        return partidoResponse;
    }

}
