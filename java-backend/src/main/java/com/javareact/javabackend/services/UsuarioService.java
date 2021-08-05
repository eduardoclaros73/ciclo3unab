package com.javareact.javabackend.services;

import java.util.ArrayList;

import com.javareact.javabackend.entities.UsuarioEntity;
import com.javareact.javabackend.exceptions.CorreoAlreadyExistsException;
import com.javareact.javabackend.exceptions.UsernameAlreadyExistsException;
import com.javareact.javabackend.repositories.UsuarioRepository;
import com.javareact.javabackend.shared.dtos.UsuarioDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UsuarioServiceInterface {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ModelMapper modelMapper;

    public UsuarioDto createUsuario(UsuarioDto usuarioDto) {
        if (usuarioRepository.findByCorreo(usuarioDto.getCorreo()) != null) {
            throw new CorreoAlreadyExistsException("Correo ya existe");
        }
        if (usuarioRepository.findByUsername(usuarioDto.getUsername()) != null) {
            throw new UsernameAlreadyExistsException("Username ya existe");
        }

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        BeanUtils.copyProperties(usuarioDto, usuarioEntity);

        usuarioEntity.setPassword(
            bCryptPasswordEncoder.encode(usuarioDto.getPassword())
        );

        UsuarioEntity createdUsuarioEntity = usuarioRepository.save(usuarioEntity);

        UsuarioDto createdUsuarioDto = new UsuarioDto();
        BeanUtils.copyProperties(createdUsuarioEntity, createdUsuarioDto);

        return createdUsuarioDto;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        UsuarioEntity usuarioEntity = usuarioRepository.findByCorreo(correo);

        if (usuarioEntity == null) {
            throw new UsernameNotFoundException("No user with " + correo + " correo");
        }

        return new User(
            usuarioEntity.getCorreo(),
            usuarioEntity.getPassword(),
            new ArrayList<>()
        );
    }

    public UsuarioDto getUsuario(String correo) {
        UsuarioEntity usuarioEntity = usuarioRepository.findByCorreo(correo);

        if (usuarioEntity == null) {
            throw new UsernameNotFoundException("No user with " + correo + " correo");
        }

        UsuarioDto usuarioDto = modelMapper.map(usuarioEntity, UsuarioDto.class);

        return usuarioDto;
    }
    
}
