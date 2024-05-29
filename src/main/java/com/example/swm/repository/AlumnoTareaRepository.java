package com.example.swm.repository;

import com.example.swm.entity.AlumnoTarea;
import com.example.swm.entity.Alumnos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoTareaRepository extends JpaRepository<AlumnoTarea, Integer> {
    @Query("SELECT at FROM AlumnoTarea at JOIN at.alumno a WHERE a.nif_alumno = :nif_alumno")
    List<AlumnoTarea> findTareasByNifAlumno(@Param("nif_alumno") String nif_alumno);
}
