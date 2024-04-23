package com.example.swm.controllers;


import com.example.swm.entity.Clases;
import com.example.swm.repository.AlumnosRepository;
import com.example.swm.repository.ClasesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alumno")
public class AlumnocController {

    @Autowired
    private AlumnosRepository alumnosRepository;

    @Autowired
    private ClasesRepository clasesRepository;


    @GetMapping("/home")
    public String mostrarClases(Model model) {
        List<Clases> clases = clasesRepository.findAll();
        if (clases.isEmpty()) {
            model.addAttribute("mensaje", "Aun no te has unido a ninguna clase.");
        } else {
            model.addAttribute("clases", clases);
        }
        return "clases";
    }

}
