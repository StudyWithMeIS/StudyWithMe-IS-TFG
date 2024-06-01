package com.example.swm.repository;


import com.example.swm.entity.Asignaturas;
import com.example.swm.entity.ProfesorAsignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfesoresAsignaturaRepository extends JpaRepository<ProfesorAsignatura, Integer> {

    @Query("SELECT pa FROM ProfesorAsignatura pa WHERE pa.profesor.nif_profesor = :nif_profesor")
    List<ProfesorAsignatura> findAsignaturasByNifProfesor(@Param("nif_profesor") String nif_profesor);

    @Query("SELECT pa FROM ProfesorAsignatura pa WHERE pa.asignatura.id_asignatura = :id_asignatura")
    List<Asignaturas> findAsignaturasNavbarByNifProfesor(@Param("id_asignatura") String id_asignatura);
}
