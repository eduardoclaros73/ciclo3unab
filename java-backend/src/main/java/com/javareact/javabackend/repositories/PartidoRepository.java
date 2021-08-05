package com.javareact.javabackend.repositories;

import java.util.List;

import com.javareact.javabackend.entities.PartidoEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidoRepository extends CrudRepository<PartidoEntity, Long> {

    @Query(
        value = "SELECT * FROM partidos ORDER BY fecha, id LIMIT :limit OFFSET :offset",
        nativeQuery = true
    )
    List<PartidoEntity> getPartidos(
        @Param("limit") short limit,
        @Param("offset") short offset
    );

}
