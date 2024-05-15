package com.example.swm.repository;

import com.example.swm.entity.Asignaturas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AsignaturasRepository extends JpaRepository<Asignaturas, Integer> {
    @Query("SELECT a FROM Asignaturas a WHERE a.nombre_asignatura = :nombre_asignatura")
    Optional<Asignaturas> findAsignaturasByName(@Param("nombre_asignatura") String nombre_asignatura);
}
