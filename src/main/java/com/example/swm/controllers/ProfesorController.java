package com.example.swm.controllers;

import com.example.swm.repository.ProfesoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {

    @Autowired
    private ProfesoresRepository profesoresRepository;

}
