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



    //---------------ALUMNO----------------------
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
            System.out.println("Alumno cargado desde el repositorio: " + alumno);
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
    public ModelAndView guardarClase(@ModelAttribute Cursos curso) {
        ModelAndView modelAndView = new ModelAndView();
        Cursos cursos = new Cursos();
        System.out.println(curso.getNombre_curso());
        cursos.setNombre_curso(curso.getNombre_curso());
        cursosRepository.save(cursos);
        modelAndView.addObject("mensaje", "Curso creada correctamente");
        modelAndView.setViewName("redirect:/addClaseAdmin");
        return modelAndView;
    }

    // TERCERO CARGA LA VISTA PARA LISTAR CLASES.
    @GetMapping("/listarClasesAdmin")
    public ModelAndView listarClases() {
        ModelAndView mv = new ModelAndView();
        List<Cursos> cursos = cursosRepository.findAll();
        mv.addObject("cursos", cursos);
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
        System.out.println(asignatura.getProfesores());
        asignaturas.setNombre_asignatura(asignatura.getNombre_asignatura());
        asignaturas.setProfesores(asignatura.getProfesores());
        asignaturasRepository.save(asignaturas);
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

    // TERCERO CARGA LA VISTA PARA LISTAR PROFESORES.
    @GetMapping("/listarProfesoresAdmin")
    public ModelAndView listarProfesores() {
        ModelAndView mv = new ModelAndView();
        List<Profesores> profesores = profesoresRepository.findAll();
        mv.addObject("profesores", profesores);
        mv.setViewName("pages/administrador/teacher/readTeacherAdmin");
        return mv;
    }


    //---------------ADMINISTRADOR----------------------
    // PRIMERO CARGA LA VISTA PARA CREAR ADMINISTRADOR.
    @GetMapping("/vistaCrearAdministradorAdmin")
    public ModelAndView mostrarPaginaAddAdministrador() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/administrador/administrator/addAdministratorAdmin");
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

    // TERCERO CARGA LA VISTA PARA LISTAR ADMINISTRADORES.
    @GetMapping("/listarAdministradoresAdmin")
    public ModelAndView listarAdministradores() {
        ModelAndView mv = new ModelAndView();
        List<Administradores> administradores = administradoresRepository.findAll();
        mv.addObject("administradores", administradores);
        mv.setViewName("pages/administrador/administrator/readAdministratorAdmin");
        return mv;
    }
}

