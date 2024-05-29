package com.example.swm.controllers;


import com.example.swm.entity.AlumnoAsignatura;
import com.example.swm.entity.AlumnoTarea;
import com.example.swm.entity.Alumnos;
import com.example.swm.entity.Asignaturas;
import com.example.swm.repository.*;
import com.example.swm.services.AlumnoAsignaturaService;
import com.example.swm.services.AlumnoTareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

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
    private AlumnoTareaService alumnoTareaService;

    @Autowired
    private AlumnoAsignaturaService alumnoAsignaturaService;



    @GetMapping("/alumnos/homeAlumnos")
    public ModelAndView homeAsignaturas(Model model, Authentication auth) {
        ModelAndView mv = new ModelAndView();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String nifAlumno = userDetails.getUsername();

        List<AlumnoAsignatura> asignaturasAlumno = alumnoAsignaturaService.findAsignaturasByNifAlumno(nifAlumno);
        List<Asignaturas> asignaturas = new ArrayList<>();

        for (AlumnoAsignatura alumnoAsignatura : asignaturasAlumno) {
            Asignaturas asignatura = alumnoAsignatura.getAsignatura();
            asignaturas.add(asignatura);
        }

        mv.addObject("asignaturas", asignaturas);
        mv.setViewName("pages/alumno/homeAlumno");
        return mv;
    }



    @GetMapping("/alumnos/viewPerfilAlumno")
    public ModelAndView perfilAlumno(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("alumno", PerfilAlumno(auth).getModel().get("alumno"));
        mv.setViewName("pages/alumno/profileAlumno");
        return mv;
    }

    @GetMapping("/alumnos/perfilAlumno")
    public ModelAndView PerfilAlumno(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        if (auth != null) {
            String username = auth.getName();
            Alumnos alumno = alumnosRepository.findAlumnosByNif(username).orElse(null);
            if (alumno != null) {
                mv.addObject("alumno", alumno);
                mv.setViewName("pages/alumno/profileAlumno");
                return mv;
            }
        }
        mv.setViewName("redirect:/login");
        return mv;
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
