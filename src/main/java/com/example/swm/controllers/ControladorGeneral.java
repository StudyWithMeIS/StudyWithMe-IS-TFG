package com.example.swm.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControladorGeneral {
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
