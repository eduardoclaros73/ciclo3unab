package com.javareact.javabackend.controllers;

import com.javareact.javabackend.models.requests.UsuarioCreateRequestModel;
import com.javareact.javabackend.models.responses.UsuarioCreateResponseModel;
import com.javareact.javabackend.services.UsuarioServiceInterface;
import com.javareact.javabackend.shared.dtos.UsuarioDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioServiceInterface usuarioService;

    @Autowired
    ModelMapper modelMapper;
    
    @GetMapping(produces = {
        MediaType.APPLICATION_XML_VALUE,
        MediaType.APPLICATION_JSON_VALUE
    })
    public UsuarioCreateResponseModel getUsuario() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getPrincipal().toString();

        UsuarioCreateResponseModel usuarioResponse = new UsuarioCreateResponseModel();
        UsuarioDto usuarioDto = usuarioService.getUsuario(email);

        BeanUtils.copyProperties(usuarioDto, usuarioResponse);

        return usuarioResponse;
    }

    @PostMapping(produces = {
        MediaType.APPLICATION_XML_VALUE,
        MediaType.APPLICATION_JSON_VALUE
    })
    public UsuarioCreateResponseModel createUsuario(@RequestBody UsuarioCreateRequestModel usuarioDetails) {
        UsuarioCreateResponseModel usuarioResponse = new UsuarioCreateResponseModel();
        UsuarioDto usuarioDto = new UsuarioDto();

        BeanUtils.copyProperties(usuarioDetails, usuarioDto);

        UsuarioDto createdUsuarioDto = usuarioService.createUsuario(usuarioDto);
        BeanUtils.copyProperties(createdUsuarioDto, usuarioResponse);

        return usuarioResponse;
    }

}
