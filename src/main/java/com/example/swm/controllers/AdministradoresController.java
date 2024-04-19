package com.example.swm.controllers;


import com.example.swm.repository.AdministradoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adminnisrador")
public class AdministradoresController {
    @Autowired
    private AdministradoresRepository administradoresRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

}
