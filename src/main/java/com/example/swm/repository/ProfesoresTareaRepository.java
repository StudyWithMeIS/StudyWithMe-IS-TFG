package com.example.swm.repository;


import com.example.swm.entity.ProfesorTarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesoresTareaRepository extends JpaRepository<ProfesorTarea, Integer> {
    @Query("SELECT pt FROM ProfesorTarea pt JOIN pt.profesor p WHERE p.nif_profesor = :nif_profesor")
    List<ProfesorTarea> findTareasByNifProfesor(@Param("nif_profesor") String nif_profesor);
}
