package com.javareact.javabackend.repositories;

import java.util.List;

import com.javareact.javabackend.entities.EquipoEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends CrudRepository<EquipoEntity, Long> {

    EquipoEntity findByNombre(String nombre);

    @Query(
        value = "SELECT * FROM equipos",
        nativeQuery = true
    )
    List<EquipoEntity> getEquipos();

}
