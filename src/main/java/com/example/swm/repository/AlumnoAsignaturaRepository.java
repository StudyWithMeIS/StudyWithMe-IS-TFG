package com.example.swm.repository;

import com.example.swm.entity.AlumnoAsignatura;
import com.example.swm.entity.Asignaturas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlumnoAsignaturaRepository extends JpaRepository<AlumnoAsignatura, Integer> {

    @Query("SELECT at FROM AlumnoAsignatura at JOIN at.alumno a WHERE a.nif_alumno = :nif_alumno")
    List<AlumnoAsignatura> findAsignaturasByNifAlumno(@Param("nif_alumno") String nif_alumno);

    @Query("SELECT aa.asignatura FROM AlumnoAsignatura aa JOIN aa.alumno a WHERE a.nif_alumno = :nif_alumno")
    List<Asignaturas> findAsignaturasNavbarByNifAlumno(@Param("nif_alumno") String nif_alumno);
}
