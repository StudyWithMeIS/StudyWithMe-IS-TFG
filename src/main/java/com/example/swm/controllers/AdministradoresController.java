package com.example.swm.controllers;

import com.example.swm.entity.Alumnos;
import com.example.swm.repository.AdministradoresRepository;
import com.example.swm.repository.AlumnosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/administradores")
public class AdministradoresController {
    @Autowired
    private AdministradoresRepository administradoresRepository;

    @Autowired
    private AlumnosRepository alumnosRepository;

    // PRIMERO CARGA LA VISTA PARA CREAR ALUMNO.
    @GetMapping("/vistaCrearAlumnoAdmin")
    public ModelAndView mostrarPaginaAddAlumnos() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/administradores/alumnos/addAlumnosAdmin.html");
        return mv;
    }

    // SEGUNDO CARGA EL METODO QUE LLAMA DESDE EL FORMULARIO DE LA VISTA DE CREAR ALUMNO.
    @RequestMapping("/guardarAlumno")
    public ModelAndView guardarAlumno(@ModelAttribute Alumnos alumno) {
        ModelAndView modelAndView = new ModelAndView();
        Alumnos alumnos = new Alumnos();
        System.out.println(alumno.getNif_alumno());
        System.out.println(alumno.getNombre_madre_alumno());
        System.out.println(alumno.getEmail_alumno());
        System.out.println(alumno.getPassword_alumno());
        System.out.println(alumno.getNombre_padre_alumno());
        System.out.println(alumno.getNombre_madre_alumno());
        alumnos.setNif_alumno(alumno.getNif_alumno());
        alumnos.setNombre_alumno(alumno.getNombre_alumno());
        alumnos.setEmail_alumno(alumno.getEmail_alumno());
        alumnos.setPassword_alumno(alumno.getPassword_alumno());
        alumnos.setNombre_padre_alumno(alumno.getNombre_padre_alumno());
        alumnos.setNombre_madre_alumno(alumno.getNombre_madre_alumno());
        alumnosRepository.save(alumnos);
        modelAndView.addObject("mensaje", "Alumno creado correctamente");
        modelAndView.setViewName("redirect:/addAlumnosAdmin");
        return modelAndView;
    }

}