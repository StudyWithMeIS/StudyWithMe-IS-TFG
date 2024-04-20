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

import org.springframework.ui.Model;
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
    public ModelAndView index(Authentication auth, Model model) {
        ModelAndView mv = new ModelAndView();

        String imgConcursoTalento = "src/main/resources/templates/images/index/concursoTalento.webp";
        model.addAttribute("imgConcursoTalento", imgConcursoTalento);

        String imgConferenciaEducativa = "src/main/resources/templates/images/index/conferenciaEducativa.webp";
        model.addAttribute("imgConferenciaEducativa", imgConferenciaEducativa);

        String imgDiaDeporte = "src/main/resources/templates/images/index/diaDeporte.webp";
        model.addAttribute("imgDiaDeporte", imgDiaDeporte);

        String imgEntregaCertificados = "src/main/resources/templates/images/index/entregaCertificados.webp";
        model.addAttribute("imgEntregaCertificados", imgEntregaCertificados);

        String imgExploracionCientifica = "src/main/resources/templates/images/index/exploracionCientifica.webp";
        model.addAttribute("imgExploracionCientifica", imgExploracionCientifica);

        String imgFestivalCultural = "src/main/resources/templates/images/index/festivalCultural.webp";
        model.addAttribute("imgFestivalCultural", imgFestivalCultural);

        String imgInfantil = "templates/images/infantil.webp";
        model.addAttribute("imgInfantil", imgInfantil);

        String imgTallerArte = "templates/images/index/tallerArte.webp";
        model.addAttribute("imgTallerArte", imgTallerArte);

        mv.addObject("userAuthenticated", isAuthenticated(auth));
        mv.setViewName("index");
        return mv;
    }

    private boolean isAuthenticated(Authentication authentication) {
        return authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails;
    }


    @RequestMapping("/join")
    public ModelAndView join() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("join");
        return mv;
    }

    @RequestMapping("/knowUs")
    public ModelAndView knowUs() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("knowUs");
        return mv;
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
