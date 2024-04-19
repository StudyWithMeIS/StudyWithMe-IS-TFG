package com.example.swm.controllers;


import com.example.swm.entity.Clase;
import com.example.swm.repository.AlumnoRepository;
import com.example.swm.repository.ClaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alumno")
public class AlumnocController {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private ClaseRepository claseRepository;


    @GetMapping("/home")
    public String mostrarClases(Model model) {
        List<Clase> clases = claseRepository.findAll();
        if (clases.isEmpty()) {
            model.addAttribute("mensaje", "Aun no te has unido a ninguna clase.");
        } else {
            model.addAttribute("clases", clases);
        }
        return "clases";
    }

}
