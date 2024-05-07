package com.example.swm.repository;

import com.example.swm.entity.Administradores;
import com.example.swm.entity.Profesores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfesoresRepository extends JpaRepository<Profesores, Integer> {
    @Query("SELECT p FROM Profesores p WHERE p.nif_profesor = :nif_profesor")
    Optional<Profesores> findProfesoresByNif(@Param("nif_profesor") String nif_profesor);
}
