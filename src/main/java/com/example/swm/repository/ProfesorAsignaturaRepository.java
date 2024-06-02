package com.example.swm.repository;

import com.example.swm.entity.ProfesorAsignatura;
import com.example.swm.entity.Profesores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfesorAsignaturaRepository extends JpaRepository<ProfesorAsignatura, Integer> {

    @Query("SELECT pa.profesor FROM ProfesorAsignatura pa WHERE pa.asignatura.id_asignatura = :idAsignatura")
    List<Profesores> findProfesoresByAsignaturaId(@Param("idAsignatura") int idAsignatura);
}
