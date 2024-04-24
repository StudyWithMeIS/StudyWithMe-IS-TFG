package com.example.swm.controllers;

import com.example.swm.entity.Alumnos;
import com.example.swm.entity.Asignaturas;
import com.example.swm.entity.Clases;
import com.example.swm.repository.AdministradoresRepository;
import com.example.swm.repository.AlumnosRepository;
import com.example.swm.repository.AsignaturasRepository;
import com.example.swm.repository.ClasesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/administradores")
public class AdministradoresController {

    @Autowired
    private AdministradoresRepository administradoresRepository;

    @Autowired
    private AlumnosRepository alumnosRepository;

    @Autowired
    private ClasesRepository clasesRepository;

    @Autowired
    private AsignaturasRepository asignaturasRepository;



    //---------------ALUMNO----------------------
    // PRIMERO CARGA LA VISTA PARA CREAR ALUMNO.
    @GetMapping("/vistaCrearAlumnoAdmin")
    public ModelAndView mostrarPaginaAddAlumnos() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/administrador/student/addStudientAdmin");
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

    // TERCERO CARGA LA VISTA PARA LISTAR ALUMNOS.
    @GetMapping("/listarAlumnosAdmin")
    public ModelAndView listarAlumnos() {
        ModelAndView mv = new ModelAndView();
        List<Alumnos> alumnos = alumnosRepository.findAll();
        mv.addObject("alumnos", alumnos);
        mv.setViewName("pages/administrador/student/readStudientAdmin");
        return mv;
    }


    //-----------------CURSO----------------------
    // PRIMERO CARGA LA VISTA PARA CREAR CLASE.
    @GetMapping("/vistaCrearClaseAdmin")
    public ModelAndView mostrarPaginaAddClase() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/administrador/grade/addGradeAdmin");
        return mv;
    }

    // SEGUNDO CARGA EL METODO QUE LLAMA DESDE EL FORMULARIO DE LA VISTA DE CREAR CLASE.
    @RequestMapping("/guardarClase")
    public ModelAndView guardarClase(@ModelAttribute Clases clase) {
        ModelAndView modelAndView = new ModelAndView();
        Clases clases = new Clases();
        System.out.println(clase.getNombre_clase());
        clases.setNombre_clase(clase.getNombre_clase());
        modelAndView.addObject("mensaje", "Clase creada correctamente");
        modelAndView.setViewName("redirect:/addClaseAdmin");
        return modelAndView;
    }

    // TERCERO CARGA LA VISTA PARA LISTAR CLASES.
    @GetMapping("/listarClasesAdmin")
    public ModelAndView listarClases() {
        ModelAndView mv = new ModelAndView();
        List<Clases> clases = clasesRepository.findAll();
        mv.addObject("clases", clases);
        mv.setViewName("pages/administrador/grade/readGradeAdmin");
        return mv;
    }

    //-----------------ASIGNATURA----------------------
    // PRIMERO CARGA LA VISTA PARA CREAR ASIGNATURA.
    @GetMapping("/vistaCrearAsignaturaAdmin")
    public ModelAndView mostrarPaginaAddAsignatura() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/administrador/subject/addSubjectAdmin");
        return mv;
    }

    // SEGUNDO CARGA EL METODO QUE LLAMA DESDE EL FORMULARIO DE LA VISTA DE CREAR ASIGNATURA.
    @RequestMapping("/guardarAsignatura")
    public ModelAndView guardarAsignatura(@ModelAttribute Asignaturas asignatura) {
        ModelAndView modelAndView = new ModelAndView();
        Asignaturas asignaturas = new Asignaturas();
        System.out.println(asignatura.getNombre_asignatura());
        System.out.println(asignatura.getProfesor());
        asignaturas.setNombre_asignatura(asignatura.getNombre_asignatura());
        asignaturas.setProfesor(asignatura.getProfesor());
        modelAndView.addObject("mensaje", "Asignatura creada correctamente");
        modelAndView.setViewName("redirect:/addAsignaturaAdmin");
        return modelAndView;
    }

    // TERCERO CARGA LA VISTA PARA LISTAR ASIGNATURAS.
    @GetMapping("/listarAsignaturasAdmin")
    public ModelAndView listarAsignaturas() {
        ModelAndView mv = new ModelAndView();
        List<Asignaturas> asignaturas = asignaturasRepository.findAll();
        mv.addObject("asignaturas", asignaturas);
        mv.setViewName("pages/administrador/subject/readSubjectAdmin");
        return mv;
    }

    //-----------------PROFESOR----------------------
}

