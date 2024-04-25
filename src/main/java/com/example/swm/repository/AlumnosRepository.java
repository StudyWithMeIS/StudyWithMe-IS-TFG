package com.example.swm.repository;

import com.example.swm.entity.Alumnos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlumnosRepository extends JpaRepository<Alumnos, Integer> {
    Optional<Alumnos> findByNif_alumno(String nif_alumno);
}
