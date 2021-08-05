package com.javareact.javabackend.services;

import com.javareact.javabackend.shared.dtos.UsuarioDto;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioServiceInterface extends UserDetailsService {
    
    public UsuarioDto createUsuario(UsuarioDto userDto);
    public UsuarioDto getUsuario(String email);

}
