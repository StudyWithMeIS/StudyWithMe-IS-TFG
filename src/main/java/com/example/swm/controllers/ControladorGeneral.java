package com.example.swm.controllers;


import com.example.swm.entity.Administradores;
import com.example.swm.repository.AdministradoresRepository;
import com.example.swm.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorGeneral {
    @Autowired
    private LoginService loginService;

    @Autowired
    private AdministradoresRepository adminRepo;

    //Representa al cliente  que ha iniciado sesion.
    private Administradores admin_user;


    @RequestMapping("/")
    public ModelAndView peticionRaiz(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        if(auth!=null){
            admin_user = adminRepo.findById(auth.getName()).get();
            mv.addObject("user",admin_user);
            System.out.println("USUARIO INICIO SESION");
        }
        mv.setViewName("index");
        return mv;
    }


    @RequestMapping("/login")
    public ModelAndView peticionSesion() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/login");
        return mv;
    }

    @RequestMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

}
