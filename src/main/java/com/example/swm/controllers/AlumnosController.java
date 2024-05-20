package com.example.swm.controllers;


import com.example.swm.repository.*;
import com.example.swm.services.AlumnosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/alumnos")
public class AlumnosController {

    @Autowired
    private AlumnosRepository alumnosRepository;

    @Autowired
    private CursosRepository cursosRepository;

    @Autowired
    private AdministradoresRepository administradoresRepository;

    @Autowired
    private AsignaturasRepository asignaturasRepository;

    @Autowired
    private ProfesoresRepository profesoresRepository;

    @Autowired
    private AlumnosService alumnosService;

    @GetMapping("/alumnos/homeAsignaturas")
    public ModelAndView homeAsignaturas(Model model) {
        // Listar las asignaturas del alumno
        return new ModelAndView("pages/alumno/homeAlumno");
    }



    @GetMapping("/alumnos/viewPerfilAlumno")
    public ModelAndView perfilAlumno(Authentication auth) {
        return new ModelAndView("pages/alumno/profileAlumno");
    }











    //-------------------------------------------
    //--------------ASIGNATURAS------------------
    //-------------------------------------------

    //CALENDARIO
    @GetMapping("/asignaturas/calendario")
    public ModelAndView calendario() {
        return new ModelAndView("pages/alumno/grade/calendarAlumno");
    }

    //VER PERSONAS DE UNA ASIGNATURA
    @GetMapping("/asignaturas/verPersonas")
    public ModelAndView verAsignatura() {
        return new ModelAndView("pages/alumno/grade/verPersonasAlumno");
    }

    //VER TAREAS DE UNA ASIGNATURA
    @GetMapping("/asignaturas/verTareas")
    public ModelAndView verTareas() {
        return new ModelAndView("pages/alumno/grade/tareasAlumno");
    }

    //VER UNA TAREA DE UNA ASIGNATURA
    @GetMapping("/asignaturas/verUnaTarea")
    public ModelAndView verUnaAsignatura() {
        return new ModelAndView("pages/alumno/grade/unaTareaAlumno");
    }

    //VER TABLON
    @GetMapping("/asignaturas/verTablon")
    public ModelAndView verTablon() {
        return new ModelAndView("pages/alumno/grade/tablonAlumno");
    }




}
