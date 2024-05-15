package com.example.swm.services;

import com.example.swm.entity.Alumnos;
import com.example.swm.entity.Asignaturas;
import com.example.swm.entity.CursosAsignaturas;
import com.example.swm.entity.Tareas;
import com.example.swm.repository.AlumnosRepository;
import com.example.swm.repository.AsignaturasRepository;
import com.example.swm.repository.CursosAsignaturasRepository;
import com.example.swm.repository.TareasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.channels.AsynchronousSocketChannel;
import java.util.List;

@Service
public class AsignaturasService {
    @Autowired
    private AsignaturasRepository asignaturasRepository;

//    @Autowired
//    private CursosAsignaturasRepository cursosAsignaturasRepository;


//    public void eliminarAsignatura(int id_asignatura) {
//        Asignaturas asignatura = asignaturasRepository.findById(id_asignatura).orElse(null);
//        if (asignatura != null) {
//            List<CursosAsignaturas> cursosAsignaturas = cursosAsignaturasRepository.findCursosASignaturasByAsignaturaId(id_asignatura);
//            for (CursosAsignaturas cursosAsignatura : cursosAsignaturas) {
//                cursosAsignaturasRepository.delete(cursosAsignatura);
//            }
//            asignaturasRepository.delete(asignatura);
//            System.out.println("Asignatura eliminada correctamente.");
//        } else {
//            System.out.println("No se encontró ningúna asignatura con ID: " + id_asignatura);
//        }
//    }
}
