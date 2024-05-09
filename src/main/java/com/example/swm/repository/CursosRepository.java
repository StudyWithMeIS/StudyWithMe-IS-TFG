package com.example.swm.repository;

import com.example.swm.entity.Alumnos;
import com.example.swm.entity.Cursos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CursosRepository extends JpaRepository<Cursos, Integer> {
    @Query("SELECT c FROM Cursos c WHERE c.nombre_curso = :nombre_curso")
    Optional<Cursos> findCursosByName(@Param("nombre_curso") String nombre_curso);
}
