package com.example.swm.controllers;


import com.example.swm.entity.Alumnos;
import com.example.swm.repository.AdministradoresRepository;
import com.example.swm.repository.AlumnosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/administradores")
public class AdministradoresController {
    @Autowired
    private AdministradoresRepository administradoresRepository;

    @Autowired
    private AlumnosRepository alumnosRepository;

    /*
    @RequestMapping("/administrador/nuevo")
    public ModelAndView peticionCrearAdministrador(@Validated Administradores administrador, BindingResult resultado){
        ModelAndView mv = new ModelAndView();
        if (resultado.hasErrors()) {
            mv.setViewName("administrador-add");
            return mv;
        }
        Administradores admin1 = administradoresRepository.save(administrador);
        mv.addObject("administrador", administrador);
        mv.setViewName("administrador-saved");
        return mv;
    }
    */




    @RequestMapping("/alumnos/nuevo")
    public ModelAndView createAlumno(Alumnos alumno) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(alumno.getNif_alumno());
        System.out.println(alumno.getNombre_alumno());
        System.out.println(alumno.getEmail_alumno());
        System.out.println(alumno.getPassword_alumno());
        System.out.println(alumno.getNombre_padre_alumno());
        System.out.println(alumno.getNombre_madre_alumno());
//        alumnosRepository.save(alumno);
        System.out.println("alumno guardado");
//        modelAndView.addObject("alumno", alumno1);
        modelAndView.setViewName("addAlumnoAdmin");
        return modelAndView;
    }





}
