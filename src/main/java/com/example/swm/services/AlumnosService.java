package com.example.swm.services;

import com.example.swm.entity.Alumnos;
import com.example.swm.entity.Tareas;
import com.example.swm.repository.AlumnosRepository;
import com.example.swm.repository.TareasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnosService {
    @Autowired
    private AlumnosRepository alumnosRepository;

    @Autowired
    private TareasRepository tareasRepository;

    public void eliminarAlumno(int id_alumno) {
        Alumnos alumno = alumnosRepository.findById(id_alumno).orElse(null);
        if (alumno != null) {
            List<Tareas> tareas = tareasRepository.findTareasByAlumnoId(id_alumno);
            for (Tareas tarea : tareas) {
                tareasRepository.delete(tarea);
            }
            alumnosRepository.delete(alumno);
            System.out.println("Alumno eliminado correctamente.");
        } else {
            System.out.println("No se encontró ningún alumno con ID: " + id_alumno);
        }
    }
}
