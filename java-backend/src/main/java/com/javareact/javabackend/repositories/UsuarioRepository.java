package com.javareact.javabackend.repositories;

import com.javareact.javabackend.entities.UsuarioEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Long> {

    UsuarioEntity findByCorreo(String correo);
    UsuarioEntity findByUsername(String username);

}
