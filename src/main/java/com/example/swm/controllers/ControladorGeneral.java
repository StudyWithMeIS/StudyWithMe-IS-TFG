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




    //Representa al cliente  que ha iniciado sesion.
    private Administradores admin_user;



    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
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
