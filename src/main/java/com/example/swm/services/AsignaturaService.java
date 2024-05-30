package com.example.swm.services;


import com.example.swm.entity.Asignaturas;
import com.example.swm.repository.AsignaturasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AsignaturaService {

    @Autowired
    private AsignaturasRepository asignaturaRepository;

    // Método para obtener una asignatura por su ID
    public Asignaturas obtenerAsignaturaPorId(int id) {
        Optional<Asignaturas> optionalAsignatura = asignaturaRepository.findById(id);
        return optionalAsignatura.orElse(null);
    }
}
