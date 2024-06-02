package com.example.swm.services;

import com.example.swm.entity.ProfesorTarea;
import com.example.swm.repository.ProfesoresTareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorTareaService {

    @Autowired
    private final ProfesoresTareaRepository profesoresTareaRepository;

    @Autowired
    public ProfesorTareaService(ProfesoresTareaRepository profesoresTareaRepository) {
        this.profesoresTareaRepository = profesoresTareaRepository;
    }

    public List<ProfesorTarea> findTareasByNifProfesor(String nif_profesor) {
        return profesoresTareaRepository.findTareasByNifProfesor(nif_profesor);
    }
}
