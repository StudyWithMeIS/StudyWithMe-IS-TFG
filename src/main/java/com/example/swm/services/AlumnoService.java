package com.example.swm.services;


import com.example.swm.entity.Alumnos;
import com.example.swm.repository.AlumnosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoService {
    @Autowired
    private AlumnosRepository alumnoRepository;
    public List<Alumnos> obtenerAlumnosPorAsignatura(int idAsignatura) {
        return alumnoRepository.findAlumnosByAsignaturaId(idAsignatura);
    }
}
