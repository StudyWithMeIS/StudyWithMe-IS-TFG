package com.example.swm.services;


import com.example.swm.entity.Asignaturas;
import com.example.swm.entity.ProfesorAsignatura;
import com.example.swm.repository.ProfesoresAsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorAsignaturaService {

    @Autowired
    private final ProfesoresAsignaturaRepository profesoresAsignaturaRepository;

    @Autowired
    public ProfesorAsignaturaService(ProfesoresAsignaturaRepository profesoresAsignaturaRepository) {
        this.profesoresAsignaturaRepository = profesoresAsignaturaRepository;
    }

    public List<ProfesorAsignatura> findAsignaturasByNifProfesor(String nif_profesor) {
        return profesoresAsignaturaRepository.findAsignaturasByNifProfesor(nif_profesor);
    }

    public List<Asignaturas> findAsignaturasNavbarByNifProfesor(String nif_profesor) {
        return profesoresAsignaturaRepository.findAsignaturasNavbarByNifProfesor(nif_profesor);
    }
}
