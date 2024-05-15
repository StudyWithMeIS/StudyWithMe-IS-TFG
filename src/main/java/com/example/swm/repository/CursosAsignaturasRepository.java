package com.example.swm.repository;

import com.example.swm.entity.CursosAsignaturas;
import com.example.swm.entity.Tareas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CursosAsignaturasRepository extends JpaRepository<CursosAsignaturas, Integer> {
    @Query("SELECT ca FROM CursosAsignaturas ca WHERE ca.id_asignatura = :id_asignatura")
    List<CursosAsignaturas> findCursosASignaturasByAsignaturaId(@Param("id_asignatura") int id_asignatura);
}
