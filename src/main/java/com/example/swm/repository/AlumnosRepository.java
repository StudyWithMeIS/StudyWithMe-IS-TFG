package com.example.swm.repository;

import com.example.swm.entity.Alumnos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AlumnosRepository extends JpaRepository<Alumnos, Integer> {
    @Query("SELECT a FROM Alumnos a WHERE a.nif_alumno = :nif_alumno")
    Optional<Alumnos> findAlumnosByNif(@Param("nif_alumno") String nif_alumno);

    @Query("SELECT a FROM Alumnos a WHERE a.id_alumno IN (SELECT aa.id_alumno_asignatura FROM AlumnoAsignatura aa WHERE aa.id_alumno_asignatura = :idAsignatura)")
    List<Alumnos> findAlumnosByAsignaturaId(@Param("idAsignatura") int idAsignatura);
}