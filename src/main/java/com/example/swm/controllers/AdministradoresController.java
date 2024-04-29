package com.example.swm.controllers;

import com.example.swm.entity.*;
import com.example.swm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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

    @Autowired
    private ProfesoresRepository profesoresRepository;



    //---------------ALUMNO----------------------
    // ------------CREAR ALUMNO------------------
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
        ModelAndView mv = new ModelAndView();
        Optional<Alumnos> existingAlumno = alumnosRepository.findAlumnosByNif(alumno.getNif_alumno());
        if (existingAlumno != null) {
            mv.addObject("error", "El alumno ya existe en la base de datos");
        } else {
            Alumnos alumnos = new Alumnos();
            alumnos.setNif_alumno(alumno.getNif_alumno());
            alumnos.setNombre_alumno(alumno.getNombre_alumno());
            alumnos.setEmail_alumno(alumno.getEmail_alumno());
            alumnos.setPassword_alumno(alumno.getPassword_alumno());
            alumnos.setNombre_padre_alumno(alumno.getNombre_padre_alumno());
            alumnos.setNombre_madre_alumno(alumno.getNombre_madre_alumno());
            alumnosRepository.save(alumnos);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mv.setViewName("redirect:/administradores/vistaCrearAlumnoAdmin");
        return mv;
    }

    // PRIRMERO CARGAR LA VISYA DE TODOS LOS ALUMNOS
    // ------------MOSTRAR ALUMNO------------------
    @GetMapping("/vistaListarAlumnosAdmin")
    public ModelAndView vistaListarAlumnos() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/administrador/student/readStudientAdmin");
        mv.addObject("alumnos", listarAlumnos().getModel().get("alumnos"));
        return mv;
    }

    // SEGUNDO LLAMAR A LISTAR ALUMNOS.
    @GetMapping("/listarAlumnos")
    public ModelAndView listarAlumnos() {
        ModelAndView mv = new ModelAndView();
        List<Alumnos> alumnos = alumnosRepository.findAll();
        mv.addObject("alumnos", alumnos);
        mv.setViewName("redirect:/administradores/vistaListarAlumnosAdmin");
        return mv;
    }


    // ------------BORRAR ALUMNO------------------
    @PostMapping("/borrarAlumno/{id_alumno}")
    public ModelAndView borrarAlumno(@PathVariable("id_alumno") int id_alumno) {
        ModelAndView mv = new ModelAndView();
        Alumnos alumno = alumnosRepository.findById(id_alumno).orElse(null);
        if (alumno != null) {
            alumnosRepository.delete(alumno);
        } else {
            System.out.println("ERROR AL ELIMINAR EL ALUMNO");
        }
        mv.setViewName("redirect:/administradores/vistaListarAlumnosAdmin");
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
        clasesRepository.save(clases);
        modelAndView.addObject("mensaje", "Clase creada correctamente");
        modelAndView.setViewName("redirect:/addClaseAdmin");
        return modelAndView;
    }

    //PRINTERO CARGA LA VISTA PARA LISTAR CLASES.
    @GetMapping("/vistaListarClasesAdmin")
    public ModelAndView vistaListarClases() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/administrador/grade/readGradeAdmin");
        mv.addObject("clases", listarClases().getModel().get("clases"));
        return mv;
    }

    // SEGUNDO LLAMAR A LISTAR CLASES.
    @GetMapping("/listarClases")
    public ModelAndView listarClases() {
        ModelAndView mv = new ModelAndView();
        List<Clases> clases = clasesRepository.findAll();
        mv.addObject("clases", clases);
        mv.setViewName("redirect:/vistaListarClasesAdmin");
        return mv;
    }


//  ---> [NO FUNCIONNA] ELIMINAR CLASE  <---
    @PostMapping("/eliminarClase")
    public ModelAndView eliminarClase(@RequestParam("id_clase") int id_clase) {
        ModelAndView modelAndView = new ModelAndView();
        clasesRepository.deleteById(String.valueOf(id_clase));
        modelAndView.setViewName("redirect:/administradores/vistaListarClasesAdmin");
        return modelAndView;
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
        asignaturasRepository.save(asignaturas);
        modelAndView.addObject("mensaje", "Asignatura creada correctamente");
        modelAndView.setViewName("redirect:/addAsignaturaAdmin");
        return modelAndView;
    }


    //PRIMER CARGA LA VISTA PARA LISTAR ASIGNATURAS.
    @GetMapping("/vistaListarAsignaturasAdmin")
    public ModelAndView vistaListarAsignaturas() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/administrador/subject/readSubjectAdmin");
        mv.addObject("asignaturas", listarAsignaturas().getModel().get("asignaturas"));
        return mv;
    }

    // SEGUNDO LLAMAR A LISTAR ASIGNATURAS.
    @GetMapping("/listarAsignaturas")
    public ModelAndView listarAsignaturas() {
        ModelAndView mv = new ModelAndView();
        List<Asignaturas> asignaturas = asignaturasRepository.findAll();
        mv.addObject("asignaturas", asignaturas);
        mv.setViewName("redirect:/vistaListarAsignaturasAdmin");
        return mv;
    }

    //-----------------PROFESOR----------------------
    // PRIMERO CARGA LA VISTA PARA CREAR PROFESOR.
    @GetMapping("/vistaCrearProfesorAdmin")
    public ModelAndView mostrarPaginaAddProfesor() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/administrador/teacher/addTeacherAdmin");
        return mv;
    }

    // SEGUNDO CARGA EL METODO QUE LLAMA DESDE EL FORMULARIO DE LA VISTA DE CREAR PROFESOR.
    @RequestMapping("/guardarProfesor")
    public ModelAndView guardarProfesor(@ModelAttribute Profesores profesor) {
        ModelAndView modelAndView = new ModelAndView();
        Profesores profesores = new Profesores();
        System.out.println(profesor.getNif_profesor());
        System.out.println(profesor.getNombre_profesor());
        System.out.println(profesor.getEmail_profesor());
        profesores.setNif_profesor(profesor.getNif_profesor());
        profesores.setNombre_profesor(profesor.getNombre_profesor());
        profesores.setEmail_profesor(profesor.getEmail_profesor());
        profesores.setPassword_profesor(profesor.getPassword_profesor());
        profesoresRepository.save(profesores);
        modelAndView.addObject("mensaje", "Profesor creado correctamente");
        modelAndView.setViewName("redirect:/addProfesorAdmin");
        return modelAndView;
    }

    //PRIMER CARGA LA VISTA PARA LISTAR PROFESORES.
    @GetMapping("/vistaListarProfesoresAdmin")
    public ModelAndView vistaListarProfesores() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/administrador/teacher/readTeacherAdmin");
        mv.addObject("profesores", listarProfesores().getModel().get("profesores"));
        return mv;
    }

    // SEGUNDO LLAMAR A LISTAR PROFESORES.
    @GetMapping("/listarProfesores")
    public ModelAndView listarProfesores() {
        ModelAndView mv = new ModelAndView();
        List<Profesores> profesores = profesoresRepository.findAll();
        mv.addObject("profesores", profesores);
        mv.setViewName("redirect:/vistaListarProfesoresAdmin");
        return mv;
    }


    //---------------ADMINISTRADOR----------------------
    // PRIMERO CARGA LA VISTA PARA CREAR ADMINISTRADOR.
    @GetMapping("/vistaCrearAdministradorAdmin")
    public ModelAndView mostrarPaginaAddAdministrador() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/administrador/addAdministrador");
        return mv;
    }

    // SEGUNDO CARGA EL METODO QUE LLAMA DESDE EL FORMULARIO DE LA VISTA DE CREAR ADMINISTRADOR.
    @RequestMapping("/guardarAdministrador")
    public ModelAndView guardarAdministrador(@ModelAttribute Administradores administrador) {
        ModelAndView modelAndView = new ModelAndView();
        Administradores administradores = new Administradores();
        System.out.println(administrador.getNombre_admin());
        System.out.println(administrador.getEmail_admin());
        System.out.println(administrador.getPassword_admin());
        administradores.setNombre_admin(administrador.getNombre_admin());
        administradores.setEmail_admin(administrador.getEmail_admin());
        administradores.setPassword_admin(administrador.getPassword_admin());
        administradoresRepository.save(administradores);
        modelAndView.addObject("mensaje", "Administrador creado correctamente");
        modelAndView.setViewName("redirect:/addAdministradorAdmin");
        return modelAndView;
    }

    //PRIMER CARGA LA VISTA PARA LISTAR ADMINISTRADORES.
    @GetMapping("/vistaListarAdministradoresAdmin")
    public ModelAndView vistaListarAdministradores() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/administrador/readAdmin");
        mv.addObject("administradores", listarAdministradores().getModel().get("administradores"));
        return mv;
    }

    // SEGUNDO LLAMAR A LISTAR ADMINISTRADORES.
    @GetMapping("/listarAdministradores")
    public ModelAndView listarAdministradores() {
        ModelAndView mv = new ModelAndView();
        List<Administradores> administradores = administradoresRepository.findAll();
        mv.addObject("administradores", administradores);
        mv.setViewName("redirect:/vistaListarAdministradoresAdmin");
        return mv;
    }

    @RequestMapping("/vistaHomeAdmin")
    public ModelAndView homeAdmin() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        modelAndView.addObject("username", currentPrincipalName);
        modelAndView.setViewName("pages/administrador/homeAdmin");
        return modelAndView;
    }
}

