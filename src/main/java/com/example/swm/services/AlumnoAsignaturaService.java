package com.example.swm.services;

import com.example.swm.entity.AlumnoAsignatura;
import com.example.swm.entity.AlumnoTarea;
import com.example.swm.entity.Asignaturas;
import com.example.swm.repository.AlumnoAsignaturaRepository;
import com.example.swm.repository.AlumnoTareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoAsignaturaService {

    @Autowired
    private final AlumnoAsignaturaRepository alumnoAsignaturaRepository;

    @Autowired
    public AlumnoAsignaturaService(AlumnoAsignaturaRepository alumnoAsignaturaRepository) {
        this.alumnoAsignaturaRepository = alumnoAsignaturaRepository;
    }

    public List<AlumnoAsignatura> findAsignaturasByNifAlumno(String nif_alumno) {
        return alumnoAsignaturaRepository.findAsignaturasByNifAlumno(nif_alumno);
    }

    public List<Asignaturas> findAsignaturasNavbarByNifAlumno(String nif_alumno) {
        return alumnoAsignaturaRepository.findAsignaturasNavbarByNifAlumno(nif_alumno);
    }
}
