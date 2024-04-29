package com.example.swm.controllers;


import com.example.swm.entity.Cursos;
import com.example.swm.repository.AlumnosRepository;
import com.example.swm.repository.CursosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alumno")
public class AlumnosController {

    @Autowired
    private AlumnosRepository alumnosRepository;

    @Autowired
    private CursosRepository cursosRepository;


    @GetMapping("/home")
    public String mostrarClases(Model model) {
        List<Cursos> cursos = cursosRepository.findAll();
        if (cursos.isEmpty()) {
            model.addAttribute("mensaje", "Aun no te has unido a ninguna clase.");
        } else {
            model.addAttribute("cursos", cursos);
        }
        return "clases";
    }

}
