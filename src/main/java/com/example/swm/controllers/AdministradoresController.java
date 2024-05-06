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
    // PRIMERO CARGA LA VISTA PARA CREAR ALUMNO.
    @GetMapping("/alumnos/viewCrearAlumnoAdmin")
    public ModelAndView viewAddAlumnosAdmin() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/administrador/student/addStudientAdmin");
        return mv;
    }

    // SEGUNDO CARGA EL METODO QUE LLAMA DESDE EL FORMULARIO DE LA VISTA DE CREAR ALUMNO.
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
            mv.setViewName("redirect:/administradores/alumnos/viewCrearAlumnoAdmin");
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
            mv.setViewName("redirect:/administradores/alumnos/viewCrearAlumnoAdmin");
            return mv;
        }
    }




    // ------------MOSTRAR ALUMNO------------------
    @GetMapping("/alumnos/viewListarAlumnosAdmin")
    public ModelAndView viewListarAlumnosAdmin() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/administrador/student/readStudientAdmin");
        mv.addObject("alumnos", listarAlumnosAdmin().getModel().get("alumnos"));
        return mv;
    }

    @GetMapping("/alumnos/listarAlumnosAdmin")
    public ModelAndView listarAlumnosAdmin() {
        ModelAndView mv = new ModelAndView();
        List<Alumnos> alumnos = alumnosRepository.findAll();
        mv.addObject("alumnos", alumnos);
        mv.setViewName("redirect:/administradores/alumnos/viewListarAlumnosAdmin");
        return mv;
    }





    //------------ACTUALIZAR ALUMNOS------------------
    @GetMapping("/alumnos/viewActualizarAlumnoAdmin/{id_alumno}")
    public ModelAndView viewUpdateAlumnosAdmin(@PathVariable("id_alumno") int id_alumno, @ModelAttribute("alumno") Alumnos alumno, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        Optional<Alumnos> alumnoOptional = alumnosRepository.findById(id_alumno);
        if (alumnoOptional.isPresent()) {
            mv.addObject("alumno", alumno);
            mv.setViewName("pages/administrador/student/updateStudientAdmin");
        } else {
            mv.setViewName("redirect:/administradores/alumnos/viewListarAlumnosAdmin");
        }
        return mv;
    }



        @PostMapping("/alumnos/actualizarAlumno/{id_alumno}")
        public ModelAndView actualizarAlumno(@PathVariable("id_alumno") int id_alumno, @ModelAttribute("alumno") Alumnos alumno, BindingResult result) {
            ModelAndView mv = new ModelAndView();
            if (result.hasErrors() || alumno.getNif_alumno().isEmpty() || alumno.getNombre_alumno().isEmpty() || alumno.getEmail_alumno().isEmpty() || alumno.getPassword_alumno().isEmpty() || alumno.getNombre_madre_alumno().isEmpty() || alumno.getNombre_padre_alumno().isEmpty()) {
                mv.addObject("error", "Por favor, completa todos los campos obligatorios.");
                mv.setViewName("redirect:/administradores/alumnos/viewListarAlumnosAdmin");
                return mv;
            }
            int idAlumno = alumno.getId_alumno();
            if (alumnosRepository.existsById(idAlumno)) {
                alumnosRepository.save(alumno);
                mv.setViewName("redirect:/administradores/alumnos/viewListarAlumnosAdmin");
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
            mv.setViewName("redirect:/administradores/alumnos/viewListarAlumnosAdmin");
        } catch (DataAccessException e) {
            System.out.println("Error al eliminar el alumno: " + e.getMessage());
            mv.addObject("error", "Error al eliminar el alumno");
            mv.setViewName("redirect:/administradores/alumnos/viewListarAlumnosAdmin");
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

    @GetMapping("/administradores/listarAlumnosAdmin")
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
}// CLOSE CLASS ADMINSTRADORCONTROLLER

