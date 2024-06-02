package com.example.swm.controllers;


import com.example.swm.entity.*;
import com.example.swm.repository.*;
import com.example.swm.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private AsignaturaService asignaturaService;

    @Autowired
    private TareaService tareaService;

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private ProfesorService profesorService;



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



    //---------MOSTRAR ASIGNATURA-----------
    @GetMapping("/asignatura/{id}")
    public ModelAndView mostrarTablonClase(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView();
        Asignaturas asignatura = asignaturaService.obtenerAsignaturaPorId(id);
        mv.addObject("asignatura", asignatura);

        List<Tareas> tareas = tareaService.obtenerTareasPorAsignatura(id);
        mv.addObject("tareas", tareas);

        mv.setViewName("pages/alumno/grade/listBoardTaskAlumno");
        return mv;
    }











    //-------------------------------------------
    //--------------ASIGNATURAS------------------
    //-------------------------------------------

    //CALENDARIO
    @GetMapping("/asignatura/calendario/{idAsignatura}")
    public ModelAndView calendario(@PathVariable("idAsignatura") int idAsignatura) {
        ModelAndView mv = new ModelAndView();
        Asignaturas asignatura = asignaturaService.obtenerAsignaturaPorId(idAsignatura);
        mv.addObject("asignatura", asignatura);
        mv.setViewName("pages/alumno/grade/calendarAlumno");
        return mv;
    }

    //VER PERSONAS DE UNA ASIGNATURA
    @GetMapping("/asignatura/verPersonas/{idAsignatura}")
    public ModelAndView verAsignatura(@PathVariable("idAsignatura") int idAsignatura) {
        ModelAndView mv = new ModelAndView();

        Asignaturas asignatura = asignaturaService.obtenerAsignaturaPorId(idAsignatura);
        mv.addObject("asignatura", asignatura);

        List<Profesores> profesores = profesorService.obtenerProfesoresPorAsignatura(idAsignatura);
        mv.addObject("profesores", profesores);

        List<Alumnos> alumnos = alumnoService.obtenerAlumnosPorAsignatura(idAsignatura);
        mv.addObject("alumnos", alumnos);


        mv.setViewName("pages/alumno/grade/listPersonsSubjectAlumno");
        return mv;
    }

    //VER TAREAS DE UNA ASIGNATURA (TRABAJO DE CLASE)
    @GetMapping("/asignatura/verTareas/{idAsignatura}")
    public ModelAndView verTareas(@PathVariable("idAsignatura") int idAsignatura) {
        ModelAndView mv = new ModelAndView();

        Asignaturas asignatura = asignaturaService.obtenerAsignaturaPorId(idAsignatura);
        mv.addObject("asignatura", asignatura);

        List<Tareas> tareas = tareaService.obtenerTareasPorAsignatura(idAsignatura);
        mv.addObject("tareas", tareas);

        mv.setViewName("pages/alumno/grade/listTaskAlumno");
        return mv;
    }

    //VER UNA TAREA DE UNA ASIGNATURA
    @GetMapping("/asignatura/verUnaTarea/{idTarea}")
    public ModelAndView verUnaTarea(@PathVariable("idTarea") int idTarea) {
        ModelAndView mv = new ModelAndView();

        Tareas tarea = tareaService.obtenerTareaPorId(idTarea);
        mv.addObject("tarea", tarea);

        mv.setViewName("pages/alumno/grade/oneTaskAlumno");

        return mv;
    }

}//CLOSE ALUMNOSCONTROLLER
