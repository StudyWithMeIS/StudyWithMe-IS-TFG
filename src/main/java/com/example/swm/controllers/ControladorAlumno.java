package com.example.swm.controllers;


import com.example.swm.repository.AlumnoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alumnos")
public class ControladorAlumno {

    @Autowired
    private AlumnoInterface alumnos;

}
