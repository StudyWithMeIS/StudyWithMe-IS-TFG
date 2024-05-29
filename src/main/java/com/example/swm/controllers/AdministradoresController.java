package com.example.swm.controllers;

import com.example.swm.entity.*;
import com.example.swm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder;

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
    private TareasRepository tareasRepository;



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
                String contraseñaEncriptada = passwordEncoder.encode(alumno.getPassword_alumno());
                System.out.println(contraseñaEncriptada);

                Alumnos alumnos = new Alumnos();
                alumnos.setNif_alumno(nifAlumno);
                alumnos.setNombre_alumno(nombreAlumno);
                alumnos.setEmail_alumno(emailAlumno);
                alumnos.setPassword_alumno(contraseñaEncriptada);
                alumnos.setNombre_padre_alumno(nombrePadreAlumno);
                alumnos.setNombre_madre_alumno(nombreMadreAlumno);
                alumnosRepository.save(alumnos);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mv.setViewName("redirect:/administradores/alumnos/viewListarAlumnosAdministradores");
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
                mv.setViewName("redirect:/administradores/alumnos/viewActualizarAlumnoAdministradores/{id_alumno}");
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
                mv.setViewName("redirect:/administradores/alumnos/viewActualizarAlumnoAdministradores/{id_alumno}");
            }
            return mv;
        }


    // ------------BORRAR ALUMNO------------------
    @PostMapping("/alumnos/borrarAlumno/{id_alumno}")
    public ModelAndView borrarAlumno(@PathVariable("id_alumno") int id_alumno_tarea) {
        ModelAndView mv = new ModelAndView();
        try {
            alumnosRepository.deleteById(id_alumno_tarea);
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
    public ModelAndView mostrarPaginaHomeAdministrador(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/administrador/homeAdministrador");
        mv.addObject("admin", homeAdministrador(auth).getModel().get("admin"));
        return mv;
    }


    @RequestMapping("/administradores/homeAdministrador")
    public ModelAndView homeAdministrador(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        if (auth != null) {
            String username = auth.getName();
            Administradores admin = administradoresRepository.findAdministradorByNif(username).orElse(null);
            if (admin != null) {
                mv.addObject("admin", admin);
                mv.setViewName("redirect:/administradores/administradores/homeAdministrador");
                return mv;
            }
        }
        mv.setViewName("redirect:/login");
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
                String contraseñaEncriptada = passwordEncoder.encode(administrador.getPassword_admin());

                Administradores administradores = new Administradores();
                administradores.setNif_admin(nifAdministrador);
                administradores.setNombre_admin(nombreAdministrador);
                administradores.setEmail_admin(emailAdministrador);
                administradores.setPassword_admin(contraseñaEncriptada);
                administradoresRepository.save(administradores);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mv.setViewName("redirect:/administradores/administradores/viewListarAdministradores");
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
            mv.setViewName("redirect:/administradores/administradores/viewActualizarAdministradores/{id_admin}");
            return mv;
        }
        int idAdministrador = administrador.getId_admin();
        if (administradoresRepository.existsById(idAdministrador)) {
            String nifAdministrador = administrador.getNif_admin().toLowerCase();
            String nombreAdministrador = administrador.getNombre_admin().toLowerCase();
            String emailAdministrador = administrador.getEmail_admin().toLowerCase();
            String contraseñaEncriptada = passwordEncoder.encode(administrador.getPassword_admin());

            administrador.setNif_admin(nifAdministrador);
            administrador.setNombre_admin(nombreAdministrador);
            administrador.setEmail_admin(emailAdministrador);
            administrador.setPassword_admin(contraseñaEncriptada);

            administradoresRepository.save(administrador);
            mv.setViewName("redirect:/administradores/administradores/viewListarAdministradores");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            mv.addObject("error", "El administrador no existe en la base de datos");
            mv.setViewName("redirect:/administradores/administradores/viewActualizarAdministradores/{id_admin}");
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
    
    
    
    //------------PERFIL ADMINISTRADOR------------

    @GetMapping("/administradores/viewPerfilAdministrador")
    public ModelAndView viewPerfilAdministrador(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/administrador/profileAdministrador");
        mv.addObject("admin", PerfilAdministrador(auth).getModel().get("admin"));
        return mv;
    }


    @GetMapping("/administradores/perfilAdministrador")
    public ModelAndView PerfilAdministrador(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        if (auth != null) {
            String username = auth.getName();
            Administradores admin = administradoresRepository.findAdministradorByNif(username).orElse(null);
            if (admin != null) {
                mv.addObject("admin", admin);
                mv.setViewName("pages/administrador/profileAdministrador");
                return mv;
            }
        }
        mv.setViewName("redirect:/login");
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
                String contraseñaEncriptada = passwordEncoder.encode(profesor.getPassword_profesor());

                Profesores profesores = new Profesores();
                profesores.setNif_profesor(nifProfesor);
                profesores.setNombre_profesor(nombreProfesor);
                profesores.setEmail_profesor(emailProfesor);
                profesores.setPassword_profesor(contraseñaEncriptada);
                profesoresRepository.save(profesores);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mv.setViewName("redirect:/administradores/profesor/viewListarProfesoresAdministradores");
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
            mv.setViewName("redirect:/administradores/profesor/viewActualizarProfesorAdministradores/{id_profesor}");
            return mv;
        }
        int idProfesor = profesor.getId_profesor();
        if (profesoresRepository.existsById(idProfesor)) {
            String nifProfesor = profesor.getNif_profesor().toLowerCase();
            String nombreProfesor = profesor.getNombre_profesor().toLowerCase();
            String emailProfesor = profesor.getEmail_profesor().toLowerCase();
            String contraseñaEncriptada = passwordEncoder.encode(profesor.getPassword_profesor());

            profesor.setNif_profesor(nifProfesor);
            profesor.setNombre_profesor(nombreProfesor);
            profesor.setEmail_profesor(emailProfesor);
            profesor.setPassword_profesor(contraseñaEncriptada);

            profesoresRepository.save(profesor);
            mv.setViewName("redirect:/administradores/profesor/viewListarProfesoresAdministradores");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            mv.addObject("error", "El profesor no existe en la base de datos");
            mv.setViewName("redirect:/administradores/profesor/viewActualizarProfesorAdministradores/{id_profesor}");
        }
        return mv;
    }



    //-----------BORRAR PROFESOR-------------
    @PostMapping("/profesor/borrarProfesorAdministrador/{id_profesor}")
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















    //-------------------------------------------
    //---------------CURSOS---------------------
    //-------------------------------------------

    //------------CREAR CURSO---------------------
    @GetMapping("/curso/viewCrearCursoAdministrador")
    public ModelAndView mostrarPaginaAddCursos() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/administrador/grade/addGradeAdmin");
        return mv;
    }

    @RequestMapping("/curso/guardarCurso")
    public ModelAndView guardarCurso(@ModelAttribute Cursos curso, @Validated BindingResult result) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors() || curso.getNombre_curso().isEmpty()) {
            System.out.println(result.getAllErrors().toString());
            mv.addObject("error", "Por favor, completa todos los campos obligatorios.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mv.setViewName("redirect:/administradores/curso/viewCrearCursoAdministrador");
            return mv;
        }else{
            String nombreCurso = curso.getNombre_curso().toLowerCase();
            Optional<Cursos> existingCursos = cursosRepository.findCursosByName(curso.getNombre_curso());
            if (existingCursos.isPresent()) {
                mv.addObject("error", "El curso ya existe en la base de datos");
            } else {
                Cursos cursos = new Cursos();
                cursos.setNombre_curso(nombreCurso);
                cursosRepository.save(cursos);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mv.setViewName("redirect:/administradores/curso/viewListarCursosAdministradores");
            return mv;
        }
    }




    //---------MOSTRAR CURSOS--------------
    @GetMapping("/curso/viewListarCursosAdministradores")
    public ModelAndView viewListarCursos() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/administrador/grade/readGradeAdmin");
        mv.addObject("cursos", listarCursos().getModel().get("cursos"));
        return mv;
    }

    @GetMapping("/curso/listarCursosAdmin")
    public ModelAndView listarCursos() {
        ModelAndView mv = new ModelAndView();
        List<Cursos> cursos = cursosRepository.findAll();
        mv.addObject("cursos", cursos);
        mv.setViewName("redirect:/administradores/curso/viewListarCursosAdministradores");
        return mv;
    }






    //----------ACTUALIZAR CURSOS------------

    @GetMapping("/curso/viewActualizarCursosAdministradores/{id_curso}")
    public ModelAndView viewUpdateCursosAdministrador(@PathVariable("id_curso") int id_curso, @ModelAttribute("curso") Cursos curso, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        Optional<Cursos> cursosOptional = cursosRepository.findById(id_curso);
        if (cursosOptional.isPresent()) {
            mv.addObject("curso", curso);
            mv.setViewName("pages/administrador/grade/updateGradeAdmin");
        } else {
            mv.setViewName("redirect:/administradores/curso/viewListarCursosAdministradores");
        }
        return mv;
    }

    @PostMapping("/curso/actualizarCurso/{id_curso}")
    public ModelAndView actualizarCurso(@PathVariable("id_curso") int id_curso, @ModelAttribute("curso") Cursos curso, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors() || curso.getNombre_curso().isEmpty()) {
            mv.addObject("error", "Por favor, completa todos los campos obligatorios.");
            mv.setViewName("redirect:/administradores/curso/viewActualizarCursosAdministradores/{id_curso}");
            return mv;
        }
        int idCurso = curso.getId_curso();
        if (cursosRepository.existsById(idCurso)) {
            String nombreCurso = curso.getNombre_curso().toLowerCase();
            curso.setNombre_curso(nombreCurso);

            cursosRepository.save(curso);
            mv.setViewName("redirect:/administradores/curso/viewListarCursosAdministradores");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            mv.addObject("error", "El curso no existe en la base de datos");
            mv.setViewName("redirect:/administradores/curso/viewActualizarCursosAdministradores/{id_curso}");
        }
        return mv;
    }



    //-----------BORRAR CURSOS-------------
    @PostMapping("/curso/borrarCursoAdministrador/{id_curso}")
    public ModelAndView borrarCurso(@PathVariable("id_curso") int id_curso) {
        ModelAndView mv = new ModelAndView();
        try {
            cursosRepository.deleteById(id_curso);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mv.setViewName("redirect:/administradores/curso/viewListarCursosAdministradores");
        } catch (DataAccessException e) {
            System.out.println("Error al eliminar el curso: " + e.getMessage());
            mv.addObject("error", "Error al eliminar el curso");
            mv.setViewName("redirect:/administradores/curso/viewListarCursosAdministradores");
        }
        return mv;
    }












    //-------------------------------------------
    //---------------ASIGNATURAS-----------------
    //-------------------------------------------

    //-------------CREAR ASIGNATURAS-------------

    @GetMapping("/asignatura/viewCrearAsignaturasAdministrador")
    public ModelAndView mostrarPaginaAddAsignatura() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/administrador/subject/addSubjectAdmin");
        return mv;
    }

    @RequestMapping("/asignatura/guardarAsignatura")
    public ModelAndView guardarAsignatura(@ModelAttribute @Validated Asignaturas asignatura, BindingResult result) {
        ModelAndView mv = new ModelAndView();

        // Validar campos obligatorios y mostrar errores si los hay
        if (result.hasErrors() || asignatura.getNombre_asignatura().isEmpty()
                || asignatura.getDescripcion_asignatura().isEmpty()
                || asignatura.getDetalle_asignatura().isEmpty()) {
            mv.addObject("error", "Por favor, completa todos los campos obligatorios.");
            mv.setViewName("redirect:/administradores/asignatura/viewCrearAsignaturasAdministrador");
            return mv;
        }

        // Convertir los nombres a minúsculas para uniformidad
        String nombreAsignatura = asignatura.getNombre_asignatura().toLowerCase();
        String imagenAsignatura = asignatura.getImagen_asignatura() != null ? asignatura.getImagen_asignatura() : "";
        String descripcionAsignatura = asignatura.getDescripcion_asignatura().toLowerCase();
        String detalleAsignatura = asignatura.getDetalle_asignatura().toLowerCase();

        // Comprobar si la asignatura ya existe
        Optional<Asignaturas> existingAsignaturas = asignaturasRepository.findAsignaturasByName(asignatura.getNombre_asignatura());
        if (existingAsignaturas.isPresent()) {
            mv.addObject("error", "La asignatura ya existe en la base de datos.");
            mv.setViewName("redirect:/administradores/asignatura/viewCrearAsignaturasAdministrador");
        } else {
            // Crear nueva asignatura y guardar en la base de datos
            Asignaturas nuevaAsignatura = new Asignaturas();
            nuevaAsignatura.setNombre_asignatura(nombreAsignatura);
            nuevaAsignatura.setImagen_asignatura(imagenAsignatura);
            nuevaAsignatura.setDescripcion_asignatura(descripcionAsignatura);
            nuevaAsignatura.setDetalle_asignatura(detalleAsignatura);

            asignaturasRepository.save(nuevaAsignatura);
        }

        mv.setViewName("redirect:/administradores/asignatura/viewListarAsignaturasAdministradores");
        return mv;
    }



    //---------MOSTRAR ASIGNATURAS--------------
    @GetMapping("/asignatura/viewListarAsignaturasAdministradores")
    public ModelAndView viewListarAsignaturas() {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("pages/administrador/subject/readSubjectAdmin");
            mv.addObject("asignatura", new Asignaturas());
            mv.addObject("asignaturas", listarAsignaturas().getModel().get("asignaturas"));
            return mv;
    }

    @GetMapping("/asignatura/listarAsignaturaAdministrador")
    public ModelAndView listarAsignaturas() {
        ModelAndView mv = new ModelAndView();
        List<Asignaturas> asignaturas = asignaturasRepository.findAll();
        mv.addObject("asignatura", new Asignaturas());
        mv.addObject("asignaturas", asignaturas);
        mv.setViewName("redirect:/administradores/asignatura/viewListarAsignaturasAdministradores");
        return mv;
    }


    //----------ACTUALIZAR ASIGNATURAS------------

    @GetMapping("/asignatura/viewActualizarAsignaturasAdministradores/{id_asignatura}")
    public ModelAndView viewUpdateAsignaturaAdministrador(@PathVariable("id_asignatura") int id_asignatura, @ModelAttribute("curso") Asignaturas asignatura, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        Optional<Asignaturas> asignaturasOptional = asignaturasRepository.findById(id_asignatura);
        if (asignaturasOptional.isPresent()) {
            mv.addObject("asignatura", asignatura);
            mv.setViewName("pages/administrador/subject/updateSubjectAdmin");
        } else {
            mv.setViewName("redirect:/administradores/asignatura/viewListarAsignaturasAdministradores");
        }
        return mv;
    }

    @PostMapping("/asignatura/actualizarAsignatura/{id_asignatura}")
    public ModelAndView actualizarAsignatura(@PathVariable("id_asignatura") int id_asignatura, @ModelAttribute("asignatura") Asignaturas asignatura, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors() || asignatura.getNombre_asignatura().isEmpty()
                || asignatura.getDescripcion_asignatura().isEmpty()
                || asignatura.getDetalle_asignatura().isEmpty()) {
            mv.addObject("error", "Por favor, completa todos los campos obligatorios.");
            mv.setViewName("redirect:/administradores/asignatura/viewActualizarAsignaturasAdministradores/" + id_asignatura);
            return mv;
        }

        if (asignaturasRepository.existsById(id_asignatura)) {
            // Convertir los campos a minúsculas
            String nombreAsignatura = asignatura.getNombre_asignatura().toLowerCase();
            String imagenAsignatura = asignatura.getImagen_asignatura() != null ? asignatura.getImagen_asignatura() : "";
            String descripcionAsignatura = asignatura.getDescripcion_asignatura().toLowerCase();
            String detalleAsignatura = asignatura.getDetalle_asignatura().toLowerCase();

            // Actualizar los valores de la asignatura
            Asignaturas asignaturaExistente = asignaturasRepository.findById(id_asignatura).orElseThrow(() -> new IllegalArgumentException("Invalid asignatura ID"));
            asignaturaExistente.setNombre_asignatura(nombreAsignatura);
            asignaturaExistente.setImagen_asignatura(imagenAsignatura);
            asignaturaExistente.setDescripcion_asignatura(descripcionAsignatura);
            asignaturaExistente.setDetalle_asignatura(detalleAsignatura);

            // Guardar la asignatura actualizada
            asignaturasRepository.save(asignaturaExistente);

            mv.setViewName("redirect:/administradores/asignatura/viewListarAsignaturasAdministradores");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            mv.addObject("error", "La asignatura no existe en la base de datos");
            mv.setViewName("redirect:/administradores/asignatura/viewActualizarAsignaturasAdministradores/" + id_asignatura);
        }
        return mv;
    }



    //-----------BORRAR ASIGNATURAS-------------
    @PostMapping("/asignatura/borrarAsignaturaAdministrador/{id_asignatura}")
    public ModelAndView borrarAsignatura(@PathVariable("id_asignatura") int id_asignatura) {
        ModelAndView mv = new ModelAndView();
        try {
            asignaturasRepository.deleteById(id_asignatura);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mv.setViewName("redirect:/administradores/asignatura/viewListarAsignaturasAdministradores");
        } catch (DataAccessException e) {
            System.out.println("Error al eliminar la asignatura: " + e.getMessage());
            mv.addObject("error", "Error al eliminar la asignatura");
            mv.setViewName("redirect:/administradores/asignatura/viewListarAsignaturasAdministradores");
        }
        return mv;
    }











    //-------------------------------------------
    //-----------------TAREAS--------------------
    //-------------------------------------------


    //------------CREAR TAREAS---------------
    @GetMapping("/tareas/viewCrearTareaAdministrador")
    public ModelAndView mostrarPaginaAddTarea() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/administrador/task/addTaskAdmin");
        return mv;
    }

    @RequestMapping("/tareas/guardarTarea")
    public ModelAndView guardarTareas(@ModelAttribute Tareas tarea, @Validated BindingResult result) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors() || tarea.getTipo_tarea().isEmpty() || tarea.getTitulo_tarea().isEmpty() || tarea.getDescripcion_tarea().isEmpty() || tarea.getCalificacion_tarea() < 0 ) {
            System.out.println(tarea.getTipo_tarea());
            System.out.println(tarea.getTitulo_tarea());
            System.out.println(tarea.getDescripcion_tarea());
            System.out.println(tarea.getCalificacion_tarea());
            System.out.println(result.getAllErrors().toString());
            mv.addObject("error", "Por favor, completa todos los campos obligatorios.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mv.setViewName("redirect:/administradores/tareas/viewCrearTareaAdministrador");
            return mv;
        }else{
            String tipoTarea = tarea.getTipo_tarea().toLowerCase();
            String tituloTarea = tarea.getTitulo_tarea().toLowerCase();
            String descripcionTarea = tarea.getDescripcion_tarea().toLowerCase();
            Optional<Tareas> existingTarea = tareasRepository.findTareasByTitulo(tituloTarea);
            if (existingTarea.isPresent()) {
                mv.addObject("error", "La tarea ya existe en la base de datos");
            } else {
                Tareas tareas = new Tareas();
                tareas.setTipo_tarea(tipoTarea);
                tareas.setTitulo_tarea(tituloTarea);
                tareas.setDescripcion_tarea(descripcionTarea);
                tareas.setCalificacion_tarea(tarea.getCalificacion_tarea());
                tareasRepository.save(tareas);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mv.setViewName("redirect:/administradores/tareas/viewListarTareasAdministradores");
            return mv;
        }
    }





    //---------MOSTRAR TAREAS------------------
    @GetMapping("/tareas/viewListarTareasAdministradores")
    public ModelAndView viewListarTareas() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pages/administrador/task/readTaskAdmin");
        mv.addObject("tarea", new Tareas());
        mv.addObject("tareas", listarTareas().getModel().get("tareas"));
        return mv;
    }

    @GetMapping("/tareas/listarTareasAdministrador")
    public ModelAndView listarTareas() {
        ModelAndView mv = new ModelAndView();
        List<Tareas> tareas = tareasRepository.findAll();
        mv.addObject("tarea", new Tareas());
        mv.addObject("tareas", tareas);
        mv.setViewName("redirect:/administradores/tareas/viewListarTareasAdministradores");
        return mv;
    }





    //----------ACTUALIZAR TAREA------------

    @GetMapping("/tarea/viewActualizarTareasAdministradores/{id_tarea}")
    public ModelAndView viewUpdateTareasAdministrador(@PathVariable("id_tarea") int id_tarea, @ModelAttribute("tarea") Tareas tarea, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        Optional<Tareas> tareasOptional = tareasRepository.findById(id_tarea);
        if (tareasOptional.isPresent()) {
            mv.addObject("tarea", tarea);
            mv.setViewName("pages/administrador/task/updateTaskAdmin");
        } else {
            mv.setViewName("redirect:/administradores/tareas/viewCrearTareaAdministrador");
        }
        return mv;
    }

    @PostMapping("/tareas/actualizarTarea/{id_tarea}")
    public ModelAndView actualizarTarea(@PathVariable("id_tarea") int id_tarea, @ModelAttribute("tarea") Tareas tarea, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors() || tarea.getTipo_tarea().isEmpty() || tarea.getTitulo_tarea().isEmpty() || tarea.getDescripcion_tarea().isEmpty() || tarea.getCalificacion_tarea() < 0) {
            mv.addObject("error", "Por favor, completa todos los campos obligatorios.");
            mv.setViewName("redirect:/administradores/tarea/viewActualizarTareasAdministradores/{id_tarea}");
            return mv;
        }
        int idTarea = tarea.getId_tarea();

        if (tareasRepository.existsById(idTarea)) {

            String tipoTarea = tarea.getTipo_tarea().toLowerCase();
            String tituloTarea = tarea.getTitulo_tarea().toLowerCase();
            String descripcionTarea = tarea.getDescripcion_tarea().toLowerCase();

            tarea.setTipo_tarea(tipoTarea);
            tarea.setTitulo_tarea(tituloTarea);
            tarea.setDescripcion_tarea(descripcionTarea);
            tarea.setCalificacion_tarea(tarea.getCalificacion_tarea());

            tareasRepository.save(tarea);
            mv.setViewName("redirect:/administradores/tareas/viewListarTareasAdministradores");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            mv.addObject("error", "La tarea no existe en la base de datos");
            mv.setViewName("redirect:/administradores/tareas/viewActualizarTareasAdministradores/{id_tarea}");
        }
        return mv;
    }



    //-----------BORRAR TAREA----------------
    @PostMapping("/tareas/borrarTareaAdministrador/{id_tarea}")
    public ModelAndView borrarTarea(@PathVariable("id_tarea") int id_tarea) {
        ModelAndView mv = new ModelAndView();
        try {
            tareasRepository.deleteById(id_tarea);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mv.setViewName("redirect:/administradores/tareas/viewListarTareasAdministradores");
        } catch (DataAccessException e) {
            System.out.println("Error al eliminar la tarea: " + e.getMessage());
            mv.addObject("error", "Error al eliminar la tarea");
            mv.setViewName("redirect:/administradores/tareas/viewListarTareasAdministradores");
        }
        return mv;
    }



}// CLOSE CLASS ADMINSTRADORCONTROLLER

