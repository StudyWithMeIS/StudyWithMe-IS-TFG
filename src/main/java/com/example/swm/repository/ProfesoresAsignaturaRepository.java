package com.example.swm.repository;


import com.example.swm.entity.Asignaturas;
import com.example.swm.entity.ProfesorAsignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfesoresAsignaturaRepository extends JpaRepository<ProfesorAsignatura, Integer> {

    @Query("SELECT pa FROM ProfesorAsignatura pa WHERE pa.profesor.nif_profesor = :nif_profesor")
    List<ProfesorAsignatura> findAsignaturasByNifProfesor(String nif_profesor);

    @Query("SELECT pa FROM ProfesorAsignatura pa WHERE pa.asignatura.id_asignatura = :id_asignatura")
    List<Asignaturas> findProfesoresByAsignatura(Integer id_asignatura);
}
