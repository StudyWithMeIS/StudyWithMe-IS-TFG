package com.example.swm.repository;

import com.example.swm.entity.Alumnos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AlumnosRepository extends JpaRepository<Alumnos, Integer> {
    @Query("SELECT a FROM Alumnos a WHERE a.nif_alumno = :nif_alumno")
    Optional<Alumnos> findAlumnosByNif(@Param("nif_alumno") String nif_alumno);
}