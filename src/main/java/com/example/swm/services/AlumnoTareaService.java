package com.example.swm.services;

import com.example.swm.entity.AlumnoTarea;
import com.example.swm.entity.Alumnos;
import com.example.swm.repository.AlumnoTareaRepository;
import com.example.swm.repository.AlumnosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoTareaService {

    @Autowired
    private final AlumnoTareaRepository alumnoTareaRepository;

    @Autowired
    public AlumnoTareaService(AlumnoTareaRepository alumnoTareaRepository) {
        this.alumnoTareaRepository = alumnoTareaRepository;
    }

    public List<AlumnoTarea> findTareasByNifAlumno(String nif_alumno) {
        return alumnoTareaRepository.findTareasByNifAlumno(nif_alumno);
    }
}
