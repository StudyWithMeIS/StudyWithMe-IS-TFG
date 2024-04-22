package com.example.swm.controllers;


import com.example.swm.entity.Alumnos;
import com.example.swm.repository.AdministradoresRepository;
import com.example.swm.repository.AlumnosRepository;
import com.example.swm.repository.ProfesoresRepository;
import com.example.swm.services.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorGeneral {
    @Autowired
    private LoginService loginService;

    @Autowired
    private AdministradoresRepository adminRepo;

    @Autowired
    private AlumnosRepository alumnoRepo;

    @Autowired
    private ProfesoresRepository profesorRepo;


    @RequestMapping("/")
    public ModelAndView peticionIndex(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("userAuthenticated", isAuthenticated(auth));
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("/join")
    public ModelAndView peticionJoin(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("userAuthenticated", isAuthenticated(auth));
        mv.setViewName("join");
        return mv;
    }

    @RequestMapping("/knowUs")
    public ModelAndView peticionKnowUs(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("userAuthenticated", isAuthenticated(auth));
        mv.setViewName("knowUs");
        return mv;
    }

    private boolean isAuthenticated(Authentication authentication) {
        return authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails;
    }

    @RequestMapping("/login")
    public ModelAndView peticionLogin(Authentication auth) {
        ModelAndView mv = new ModelAndView();
//        if(auth != null) {
//            String username = auth.getName();
//            //Representa al cliente  que ha iniciado sesion.
//            Administradores admin_user = adminRepo.findById(username).orElse(null);
//            Alumnos alumnos_user = alumnoRepo.findById(username).orElse(null);
//            Profesores profesores_user = profesorRepo.findById(username).orElse(null);
//
//            if (admin_user != null) {
//                mv.addObject("user", admin_user);
//            } else if (alumnos_user != null) {
//                mv.addObject("user", alumnos_user);
//            } else if (profesores_user != null) {
//                mv.addObject("user", profesores_user);
//            }
//            System.out.println("USUARIO INICIÓ SESIÓN: " + username);
//        }
        mv.setViewName("pages/login");
        return mv;
    }

    @RequestMapping("/logout")
    public ModelAndView peticionLogout(HttpServletRequest request, HttpServletResponse response) {
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
    public ModelAndView peticionLoginError() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("error", true);
        mv.setViewName("pages/login");
        return mv;
    }

}
