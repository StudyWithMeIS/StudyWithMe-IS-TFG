package com.example.swm.services;

import com.example.swm.entity.Asignaturas;
import com.example.swm.entity.TareaAsignatura;
import com.example.swm.entity.Tareas;
import com.example.swm.repository.AsignaturasRepository;
import com.example.swm.repository.TareasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TareaService {
    @Autowired
    private TareasRepository tareaRepository;

    public Tareas obtenerTareaPorId(int idTarea) {
        return tareaRepository.findById(idTarea).orElse(null);
    }

    public List<Tareas> obtenerTareasPorAsignatura(int idAsignatura) {
        return tareaRepository.findTareasByAsignaturaId(idAsignatura);
    }

    public List<Tareas> obtenerTodasLasTareas() {
        return tareaRepository.findAll();
    }

    public Tareas guardarTarea(Tareas tarea) {
        return tareaRepository.save(tarea);
    }
}
