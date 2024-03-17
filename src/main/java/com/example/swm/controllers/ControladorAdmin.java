package com.example.swm.controllers;


import com.example.swm.repository.AdminInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class ControladorAdmin {
    @Autowired
    private AdminInterface adminInterface;


}
