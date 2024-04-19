package com.example.swm.controllers;


import com.example.swm.entity.Administradores;
import com.example.swm.entity.Alumno;
import com.example.swm.entity.Profesor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorGeneral {
    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

//    @RequestMapping("/login")
//    public ModelAndView login(Authentication aut) {
//        ModelAndView mv = new ModelAndView("login");
//
//        // Obtener el nombre de usuario del usuario autenticado
//        String username = aut.getName();
//
//        // Determinar el tipo de usuario y obtener su información de inicio de sesión
//        Object userDetails = aut.getPrincipal();
//        String loginInfo = "";
//
//        if (userDetails instanceof Administradores) {
//            loginInfo = ((Administradores) userDetails).devolverLogin();
//        } else if (userDetails instanceof Profesor) {
//            loginInfo = ((Profesor) userDetails).devolverLogin();
//        } else if (userDetails instanceof Alumno) {
//            loginInfo = ((Alumno) userDetails).devolverLogin();
//        }
//
//        // Agregar la información de inicio de sesión al ModelAndView
//        mv.addObject("loginInfo", loginInfo);
//
//        return mv;
//    }

}
