package com.example.swm.controllers;


import com.example.swm.entity.Administradores;
import com.example.swm.entity.Alumnos;
import com.example.swm.entity.Profesores;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorGeneral {

    @Autowired
    private LoginService loginService;

    @Autowired
    private AdministradoresRepository adminRepo;

    @Autowired
    private AlumnosRepository alumnosRepo;

    @Autowired
    private ProfesoresRepository profesoresRepo;

    @RequestMapping("/")
    public ModelAndView index(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        if (auth != null) {
            String username = auth.getName();
            //Representa al cliente  que ha iniciado sesion.
            Administradores admin_user = adminRepo.findAdministradorByNif(username).orElse(null);
            Alumnos alumno_user = alumnosRepo.findAlumnosByNif(username).orElse(null);
            Profesores profesor_user = profesoresRepo.findProfesoresByNif(username).orElse(null);

            System.out.println("AUTH HECHO");

            if (admin_user != null) {
                mv.addObject("user", admin_user);
                System.out.println("ROLE ADMIN");
            } else if (alumno_user != null) {
                mv.addObject("user", alumno_user);
                System.out.println("ROLE ALUMNO");
            } else if (profesor_user != null) {
                mv.addObject("user", profesor_user);
                System.out.println("ROLE PROFESOR");
            }
            System.out.println("USUARIO INICIÓ SESIÓN: " + username);
            mv.addObject("userAuthenticated", isAuthenticated(auth));
        }else{
            System.out.println("AUTH NO HECHO");
        }
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
