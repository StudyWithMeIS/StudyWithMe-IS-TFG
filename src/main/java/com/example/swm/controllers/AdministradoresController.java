package com.example.swm.controllers;

import com.example.swm.entity.*;
import com.example.swm.repository.*;
import com.example.swm.services.AlumnosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    private CursosRepository cursosRepository;

    @Autowired
    private AsignaturasRepository asignaturasRepository;

    @Autowired
    private ProfesoresRepository profesoresRepository;

    @Autowired
    private AlumnosService alumnosService;


    //-------------------------------------------
    //---------------ALUMNO----------------------
    //-------------------------------------------


    // ------------CREAR ALUMNO------------------
    @GetMapping("/alumnos/viewCrearAlumnoAdministradores")
    public ModelAndView viewAddAlumnosAdmin() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/administrador/student/addStudientAdmin");
        return mv;
    }

    @RequestMapping("/alumnos/guardarAlumno")
    public ModelAndView guardarAlumnosAdmin(@ModelAttribute Alumnos alumno, @Validated BindingResult result) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors() || alumno.getNif_alumno().isEmpty() || alumno.getNombre_alumno().isEmpty() || alumno.getEmail_alumno().isEmpty() || alumno.getPassword_alumno().isEmpty() || alumno.getNombre_madre_alumno().isEmpty() || alumno.getNombre_padre_alumno().isEmpty()) {
            mv.addObject("error", "Por favor, completa todos los campos obligatorios.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mv.setViewName("redirect:/administradores/alumnos/viewCrearAlumnoAdministradores");
            return mv;
        }else{
            String nifAlumno = alumno.getNif_alumno().toLowerCase();
            String nombreAlumno = alumno.getNombre_alumno().toLowerCase();
            String emailAlumno = alumno.getEmail_alumno().toLowerCase();
            String nombrePadreAlumno = alumno.getNombre_padre_alumno().toLowerCase();
            String nombreMadreAlumno = alumno.getNombre_madre_alumno().toLowerCase();
            Optional<Alumnos> existingAlumno = alumnosRepository.findAlumnosByNif(alumno.getNif_alumno());
            if (existingAlumno.isPresent()) {
                mv.addObject("error", "El alumno ya existe en la base de datos");
            } else {
                Alumnos alumnos = new Alumnos();
                alumnos.setNif_alumno(nifAlumno);
                alumnos.setNombre_alumno(nombreAlumno);
                alumnos.setEmail_alumno(emailAlumno);
                alumnos.setPassword_alumno(alumno.getPassword_alumno());
                alumnos.setNombre_padre_alumno(nombrePadreAlumno);
                alumnos.setNombre_madre_alumno(nombreMadreAlumno);
                alumnosRepository.save(alumnos);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mv.setViewName("redirect:/administradores/alumnos/viewCrearAlumnoAdministradores");
            return mv;
        }
    }




    // ------------MOSTRAR ALUMNO------------------
    @GetMapping("/alumnos/viewListarAlumnosAdministradores")
    public ModelAndView viewListarAlumnosAdmin() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/administrador/student/readStudientAdmin");
        mv.addObject("alumnos", listarAlumnosAdmin().getModel().get("alumnos"));
        return mv;
    }

    @GetMapping("/alumnos/listarAlumnosAdministradores")
    public ModelAndView listarAlumnosAdmin() {
        ModelAndView mv = new ModelAndView();
        List<Alumnos> alumnos = alumnosRepository.findAll();
        mv.addObject("alumnos", alumnos);
        mv.setViewName("redirect:/administradores/alumnos/viewListarAlumnosAdministradores");
        return mv;
    }





    //------------ACTUALIZAR ALUMNOS------------------
    @GetMapping("/alumnos/viewActualizarAlumnoAdministradores/{id_alumno}")
    public ModelAndView viewUpdateAlumnosAdmin(@PathVariable("id_alumno") int id_alumno, @ModelAttribute("alumno") Alumnos alumno, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        Optional<Alumnos> alumnoOptional = alumnosRepository.findById(id_alumno);
        if (alumnoOptional.isPresent()) {
            mv.addObject("alumno", alumno);
            mv.setViewName("pages/administrador/student/updateStudientAdmin");
        } else {
            mv.setViewName("redirect:/administradores/alumnos/viewListarAlumnosAdministradores");
        }
        return mv;
    }



        @PostMapping("/alumnos/actualizarAlumno/{id_alumno}")
        public ModelAndView actualizarAlumno(@PathVariable("id_alumno") int id_alumno, @ModelAttribute("alumno") Alumnos alumno, BindingResult result) {
            ModelAndView mv = new ModelAndView();
            if (result.hasErrors() || alumno.getNif_alumno().isEmpty() || alumno.getNombre_alumno().isEmpty() || alumno.getEmail_alumno().isEmpty() || alumno.getPassword_alumno().isEmpty() || alumno.getNombre_madre_alumno().isEmpty() || alumno.getNombre_padre_alumno().isEmpty()) {
                mv.addObject("error", "Por favor, completa todos los campos obligatorios.");
                mv.setViewName("redirect:/administradores/alumnos/viewListarAlumnosAdministradores");
                return mv;
            }
            int idAlumno = alumno.getId_alumno();
            if (alumnosRepository.existsById(idAlumno)) {
                alumnosRepository.save(alumno);
                mv.setViewName("redirect:/administradores/alumnos/viewListarAlumnosAdministradores");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                mv.addObject("error", "El alumno no existe en la base de datos");
                mv.setViewName("redirect:/administradores/alumnos/viewActualizarAlumnoAdmin");
            }
            return mv;
        }


    // ------------BORRAR ALUMNO------------------
    @PostMapping("/alumnos/borrarAlumno/{id_alumno}")
    public ModelAndView borrarAlumno(@PathVariable("id_alumno") int id_alumno_tarea) {
        ModelAndView mv = new ModelAndView();
        try {
            alumnosService.eliminarAlumno(id_alumno_tarea);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mv.setViewName("redirect:/administradores/alumnos/viewListarAlumnosAdministradores");
        } catch (DataAccessException e) {
            System.out.println("Error al eliminar el alumno: " + e.getMessage());
            mv.addObject("error", "Error al eliminar el alumno");
            mv.setViewName("redirect:/administradores/alumnos/viewListarAlumnosAdministradores");
        }
        return mv;
    }













    //-------------------------------------------
    //---------------ADMINSTRADOR----------------
    //-------------------------------------------
    @GetMapping("/administradores/viewHomeAdministrador")
    public ModelAndView mostrarPaginaHomeAdministrador() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/administrador/homeAdministrador");
        return mv;
    }



    //------------CREAR ADMINISTRADOR------------
    @GetMapping("/administradores/viewCrearAdministrador")
    public ModelAndView mostrarPaginaAddAdministrador() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/administrador/addAdministrador");
        return mv;
    }

    @RequestMapping("/administradores/guardarAdministrador")
    public ModelAndView guardarAdministrador(@ModelAttribute Administradores administrador, @Validated BindingResult result) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors() || administrador.getNif_admin().isEmpty() || administrador.getNombre_admin().isEmpty() || administrador.getEmail_admin().isEmpty() || administrador.getPassword_admin().isEmpty()) {
            mv.addObject("error", "Por favor, completa todos los campos obligatorios.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mv.setViewName("redirect:/administradores/administradores/viewCrearAdministrador");
            return mv;
        }else{
            String nifAdministrador = administrador.getNif_admin().toLowerCase();
            String nombreAdministrador = administrador.getNombre_admin().toLowerCase();
            String emailAdministrador = administrador.getEmail_admin().toLowerCase();
            Optional<Administradores> existingAdministrador = administradoresRepository.findAdministradorByNif(administrador.getNif_admin());
            if (existingAdministrador.isPresent()) {
                mv.addObject("error", "El administrador ya existe en la base de datos");
            } else {
                Administradores administradores = new Administradores();
                administradores.setNif_admin(nifAdministrador);
                administradores.setNombre_admin(nombreAdministrador);
                administradores.setEmail_admin(emailAdministrador);
                administradores.setPassword_admin(administrador.getPassword_admin());
                administradoresRepository.save(administradores);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mv.setViewName("redirect:/administradores/administradores/viewCrearAdministrador");
            return mv;
        }
    }




    //------------MOSTRAR ADMINISTRADOR------------
    @GetMapping("/administradores/viewListarAdministradores")
    public ModelAndView viewListarAdministradores() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/administrador/readAdministrador");
        mv.addObject("administradores", listarAdministradores().getModel().get("administradores"));
        return mv;
    }

    @GetMapping("/administradores/listarAdministradores")
    public ModelAndView listarAdministradores() {
        ModelAndView mv = new ModelAndView();
        List<Administradores> administradores = administradoresRepository.findAll();
        mv.addObject("administradores", administradores);
        mv.setViewName("redirect:/administradores/administradores/viewListarAdministradores");
        return mv;
    }



    //----------ACTUALIZAR ADMINISTRADOR----------

    @GetMapping("/administradores/viewActualizarAdministradores/{id_admin}")
    public ModelAndView viewUpdateAdministrador(@PathVariable("id_admin") int id_admin, @ModelAttribute("administrador") Administradores administrador, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        Optional<Administradores> administradorOptional = administradoresRepository.findById(id_admin);
        if (administradorOptional.isPresent()) {
            mv.addObject("administrador", administrador);
            mv.setViewName("pages/administrador/updateAdministrador");
        } else {
            mv.setViewName("redirect:/administradores/administradores/viewListarAdministradores");
        }
        return mv;
    }

    @PostMapping("/administradores/actualizarAdministradores/{id_admin}")
    public ModelAndView actualizarAdministrador(@PathVariable("id_admin") int id_admin, @ModelAttribute("administrador") Administradores administrador, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors() || administrador.getNif_admin().isEmpty() || administrador.getNombre_admin().isEmpty() || administrador.getEmail_admin().isEmpty() || administrador.getPassword_admin().isEmpty()) {
            mv.addObject("error", "Por favor, completa todos los campos obligatorios.");
            mv.setViewName("redirect:/administradores/administradores/viewListarAdministradores");
            return mv;
        }
        int idAdministrador = administrador.getId_admin();
        if (administradoresRepository.existsById(idAdministrador)) {
            administradoresRepository.save(administrador);
            mv.setViewName("redirect:/administradores/administradores/viewListarAdministradores");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            mv.addObject("error", "El administrador no existe en la base de datos");
            mv.setViewName("redirect:/administradores/administradores/viewListarAdministradores");
        }
        return mv;
    }




    //-----------BORRAR ADMINISTRADOR-------------
    @PostMapping("/administradores/borrarAdministrador/{id_admin}")
    public ModelAndView borrarAdministrador(@PathVariable("id_admin") int id_admin) {
        ModelAndView mv = new ModelAndView();
        try {
            administradoresRepository.deleteById(id_admin);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mv.setViewName("redirect:/administradores/administradores/viewListarAdministradores");
        } catch (DataAccessException e) {
            System.out.println("Error al eliminar el administrador: " + e.getMessage());
            mv.addObject("error", "Error al eliminar el administrador");
            mv.setViewName("redirect:/administradores/administradores/viewListarAdministradores");
        }
        return mv;
    }






    //-------------------------------------------
    //---------------PROFESORES------------------
    //-------------------------------------------


    //------------CREAR PROFESORES---------------
    @GetMapping("/profesor/viewCrearProfesorAdministrador")
    public ModelAndView mostrarPaginaAddProfesores() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/administrador/teacher/addTeacherAdmin");
        return mv;
    }

    @RequestMapping("/profesor/guardarProfesor")
    public ModelAndView guardarProfesores(@ModelAttribute Profesores profesor, @Validated BindingResult result) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors() || profesor.getNif_profesor().isEmpty() || profesor.getNombre_profesor().isEmpty() || profesor.getEmail_profesor().isEmpty() || profesor.getPassword_profesor().isEmpty()) {
            System.out.println(profesor.getNif_profesor());
            System.out.println(profesor.getNombre_profesor());
            System.out.println(profesor.getEmail_profesor());
            System.out.println(profesor.getPassword_profesor());
            System.out.println(result.getAllErrors().toString());
            mv.addObject("error", "Por favor, completa todos los campos obligatorios.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mv.setViewName("redirect:/administradores/profesor/viewCrearProfesorAdministrador");
            return mv;
        }else{
            String nifProfesor = profesor.getNif_profesor().toLowerCase();
            String nombreProfesor = profesor.getNombre_profesor().toLowerCase();
            String emailProfesor = profesor.getEmail_profesor().toLowerCase();
            Optional<Profesores> existingProfesor = profesoresRepository.findProfesoresByNif(profesor.getNif_profesor());
            if (existingProfesor.isPresent()) {
                mv.addObject("error", "El profesor ya existe en la base de datos");
            } else {
                Profesores profesores = new Profesores();
                profesores.setNif_profesor(nifProfesor);
                profesores.setNombre_profesor(nombreProfesor);
                profesores.setEmail_profesor(emailProfesor);
                profesores.setPassword_profesor(profesor.getPassword_profesor());
                profesoresRepository.save(profesores);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mv.setViewName("redirect:/administradores/profesor/viewCrearProfesorAdministrador");
            return mv;
        }
    }





    //---------MOSTRAR PROFESORES-----------
    @GetMapping("/profesor/viewListarProfesoresAdministradores")
    public ModelAndView viewListarProfesores() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/administrador/teacher/readTeacherAdmin");
        mv.addObject("profesores", listarProfesores().getModel().get("profesores"));
        return mv;
    }

    @GetMapping("/profesor/listarProfesoresAdmin")
    public ModelAndView listarProfesores() {
        ModelAndView mv = new ModelAndView();
        List<Profesores> profesores = profesoresRepository.findAll();
        mv.addObject("profesores", profesores);
        mv.setViewName("redirect:/administradores/profesor/viewListarProfesoresAdministradores");
        return mv;
    }


    //----------ACTUALIZAR PROFESOR----------

    @GetMapping("/profesor/viewActualizarProfesorAdministradores/{id_profesor}")
    public ModelAndView viewUpdateProfesorAdministrador(@PathVariable("id_profesor") int id_profesor, @ModelAttribute("profesor") Profesores profesor, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        Optional<Profesores> profesorOptional = profesoresRepository.findById(id_profesor);
        if (profesorOptional.isPresent()) {
            mv.addObject("profesor", profesor);
            mv.setViewName("pages/administrador/teacher/updateTeacherAdmin");
        } else {
            mv.setViewName("redirect:/administradores/profesor/viewListarAdministradores");
        }
        return mv;
    }

    @PostMapping("/profesor/actualizarProfesor/{id_profesor}")
    public ModelAndView actualizarAdministrador(@PathVariable("id_profesor") int id_profesor, @ModelAttribute("profesor") Profesores profesor, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors() || profesor.getNif_profesor().isEmpty() || profesor.getNombre_profesor().isEmpty() || profesor.getEmail_profesor().isEmpty() || profesor.getPassword_profesor().isEmpty()) {
            mv.addObject("error", "Por favor, completa todos los campos obligatorios.");
            mv.setViewName("redirect:/administradores/profesor/viewListarProfesoresAdministradores");
            return mv;
        }
        int idProfesor = profesor.getId_profesor();
        if (profesoresRepository.existsById(idProfesor)) {
            profesoresRepository.save(profesor);
            mv.setViewName("redirect:/administradores/profesor/viewListarProfesoresAdministradores");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            mv.addObject("error", "El profesor no existe en la base de datos");
            mv.setViewName("redirect:/administradores/profesor/viewListarProfesoresAdministradores");
        }
        return mv;
    }



    //-----------BORRAR PROFESOR-------------
    @PostMapping("/profesor/borrarAdministrador/{id_profesor}")
    public ModelAndView borrarProfesor(@PathVariable("id_profesor") int id_profesor) {
        ModelAndView mv = new ModelAndView();
        try {
            profesoresRepository.deleteById(id_profesor);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mv.setViewName("redirect:/administradores/profesor/viewListarProfesoresAdministradores");
        } catch (DataAccessException e) {
            System.out.println("Error al eliminar el profesor: " + e.getMessage());
            mv.addObject("error", "Error al eliminar el profesor");
            mv.setViewName("redirect:/administradores/profesor/viewListarProfesoresAdministradores");
        }
        return mv;
    }



}// CLOSE CLASS ADMINSTRADORCONTROLLER

