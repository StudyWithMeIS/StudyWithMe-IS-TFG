package com.example.swm.controllers;


import com.example.swm.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alumno")
public class AlumnocController {

    @Autowired
    private AlumnoRepository alumnoRepository;

}
