package com.example.swm.controllers;


import com.example.swm.entity.Administradores;
import com.example.swm.entity.Alumno;
import com.example.swm.entity.Profesor;
import com.example.swm.repository.AdministradoresRepository;
import com.example.swm.repository.AlumnoRepository;
import com.example.swm.repository.ProfesorRepository;
import com.example.swm.services.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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

    @Autowired
    private AlumnoRepository alumnoRepo;

    @Autowired
    private ProfesorRepository profesorRepo;


    //Representa al cliente  que ha iniciado sesion.
    private Administradores admin_user;
    private Alumno alumno_user;
    private Profesor profesor_user;


    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }


    @RequestMapping("/login")
    public ModelAndView login(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        if(auth != null) {
            String username = auth.getName();
            admin_user = adminRepo.findById(username).orElse(null);
            alumno_user = alumnoRepo.findById(username).orElse(null);
            profesor_user = profesorRepo.findById(username).orElse(null);

            if (admin_user != null) {
                mv.addObject("user", admin_user);
            } else if (alumno_user != null) {
                mv.addObject("user", alumno_user);
            } else if (profesor_user != null) {
                mv.addObject("user", profesor_user);
            }
            System.out.println("USUARIO INICIÓ SESIÓN: " + username);
        }
        mv.setViewName("pages/login");
        return mv;
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        response.setHeader("Refresh", "1;url=/");
        return null;
    }

    @RequestMapping("/login-error")
    public ModelAndView loginError() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("error", true);
        mv.setViewName("pages/login");
        return mv;
    }

    @RequestMapping("/enrroll")
    public ModelAndView enrroll() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/enrroll");
        return mv;
    }

    @RequestMapping("/knowUs")
    public ModelAndView knowUs() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/knowUs");
        return mv;
    }

    @RequestMapping("/error")
    public ModelAndView error() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error/errorGeneral");
        return mv;
    }

}
