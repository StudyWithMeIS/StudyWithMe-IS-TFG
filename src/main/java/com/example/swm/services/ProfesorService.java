package com.example.swm.services;


import com.example.swm.entity.Profesores;
import com.example.swm.repository.ProfesorAsignaturaRepository;
import com.example.swm.repository.ProfesoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService {
    @Autowired
    private ProfesorAsignaturaRepository profesorRepository;

    public List<Profesores> obtenerProfesoresPorAsignatura(int idAsignatura) {
        return profesorRepository.findProfesoresByAsignaturaId(idAsignatura);
    }




}
