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
import org.springframework.security.core.userdetails.UserDetails;
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


    @RequestMapping("/")
    public ModelAndView index(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("userAuthenticated", isAuthenticated(auth));
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("/join")
    public ModelAndView join(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("userAuthenticated", isAuthenticated(auth));
        mv.setViewName("join");
        return mv;
    }

    @RequestMapping("/knowUs")
    public ModelAndView knowUs(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("userAuthenticated", isAuthenticated(auth));
        mv.setViewName("knowUs");
        return mv;
    }

    private boolean isAuthenticated(Authentication authentication) {
        return authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails;
    }

    @RequestMapping("/login")
    public ModelAndView login(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        if(auth != null) {
            String username = auth.getName();
            //Representa al cliente  que ha iniciado sesion.
            Administradores admin_user = adminRepo.findById(username).orElse(null);
            Alumno alumno_user = alumnoRepo.findById(username).orElse(null);
            Profesor profesor_user = profesorRepo.findById(username).orElse(null);

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
}
