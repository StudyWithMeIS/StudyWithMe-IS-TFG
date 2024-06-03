package com.example.swm.controllers;

import com.example.swm.entity.*;
import com.example.swm.repository.ProfesoresRepository;
import com.example.swm.repository.TareasRepository;
import com.example.swm.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {

    @Autowired
    private ProfesorAsignaturaService profesorAsignaturaService;

    @Autowired
    private ProfesoresRepository profesoresRepository;

    @Autowired
    private AsignaturaService asignaturaService;

    @Autowired
    private TareaService tareaService;

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private TareasRepository tareasRepository;


    @GetMapping("/profesor/homeProfesor")
    public ModelAndView homeAsignaturas(Model model, Authentication auth) {
        ModelAndView mv = new ModelAndView();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String nifProfesor = userDetails.getUsername();

        List<ProfesorAsignatura> asignaturasProfesor = profesorAsignaturaService.findAsignaturasByNifProfesor(nifProfesor);
        List<Asignaturas> asignaturas = new ArrayList<>();

        for (ProfesorAsignatura asignaturaProfesor : asignaturasProfesor) {
            Asignaturas asignatura = asignaturaProfesor.getAsignatura();
            asignaturas.add(asignatura);
        }

        mv.addObject("asignaturas", asignaturas);
        mv.setViewName("pages/profesor/homeProfesor");
        return mv;
    }

    @GetMapping("/profesor/viewPerfilProfesor")
    public ModelAndView perfilProfesor(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("profesor", PerfilProfersor(auth).getModel().get("profesor"));
        mv.setViewName("pages/profesor/profileProfesor");
        return mv;
    }

    @GetMapping("/profesor/perfilProfesor")
    public ModelAndView PerfilProfersor(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        if (auth != null) {
            String username = auth.getName();
            Profesores profesor = profesoresRepository.findProfesoresByNif(username).orElse(null);
            if (profesor != null) {
                mv.addObject("profesor", profesor);
                mv.setViewName("pages/profesor/profileProfesor");
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

        mv.setViewName("pages/profesor/asignatura/tablonTareasProfesor");
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
        mv.setViewName("pages/profesor/asignatura/calendarioProfesor");
        return mv;
    }

    //VER PERSONAS DE UNA ASIGNATURA
    @GetMapping("/asignatura/verPersonas/{idAsignatura}")
    public ModelAndView verPersonas(@PathVariable("idAsignatura") int idAsignatura) {
        ModelAndView mv = new ModelAndView();
        Asignaturas asignatura = asignaturaService.obtenerAsignaturaPorId(idAsignatura);
        mv.addObject("asignatura", asignatura);

        List<Profesores> profesores = profesorService.obtenerProfesoresPorAsignatura(idAsignatura);
        mv.addObject("profesores", profesores);

        List<Alumnos> alumnos = alumnoService.obtenerAlumnosPorAsignatura(idAsignatura);
        mv.addObject("alumnos", alumnos);

        mv.setViewName("pages/profesor/asignatura/leerGenteAsiganaturaProfesor");
        return mv;
    }

    //VER TAREAS DE UNA ASIGNATURA (tablon)
    @GetMapping("/asignaturas/{idAsignatura}")
    public ModelAndView verTareas(@PathVariable("idAsignatura") int idAsignatura) {
        ModelAndView mv = new ModelAndView();

        Asignaturas asignatura = asignaturaService.obtenerAsignaturaPorId(idAsignatura);
        mv.addObject("asignatura", asignatura);

        List<Tareas> tareas = tareaService.obtenerTareasPorAsignatura(idAsignatura);
        mv.addObject("tareas", tareas);

        mv.setViewName("pages/profesor/asignatura/tablonTareasProfesor");
        return mv;
    }

    //VER TAREAS DE UNA ASIGNATURA
    @GetMapping("/asignatura/verUnaTarea/{idAsignatura}/{idTarea}")
    public ModelAndView verUnaTarea(@PathVariable("idTarea") int idTarea, @PathVariable("idAsignatura") int idAsignatura) {
        ModelAndView mv = new ModelAndView();

        Asignaturas asignatura = asignaturaService.obtenerAsignaturaPorId(idAsignatura);
        mv.addObject("asignatura", asignatura);

        Tareas tareas = tareaService.obtenerTareaPorId(idTarea);
        mv.addObject("tareas", tareas);

        mv.setViewName("pages/profesor/asignatura/unaTareaProfesor");
        return mv;
    }

    @PostMapping("/tareas/{id}/subirTrabajo")
    public String subirTrabajo(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("error", "Por favor, sube un archivo.");
            return "redirect:/profesores/tareas/" + id;
        }

        tareaService.guardarArchivo(id, file);

        return "redirect:/profesores/tareas/" + id;
    }

    @PostMapping("/tareas/modificarTarea")
    public String modificarTarea(@ModelAttribute("tarea") Tareas tarea, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "Por favor, corrige los errores en el formulario.");
            return "redirect:/profesores/tareas/" + tarea.getId_tarea();
        }

        tareaService.actualizarTarea(tarea);
        return "redirect:/profesores/tareas/" + tarea.getId_tarea();
    }

    @PostMapping("/tareas/calificarTarea")
    public String calificarTarea(@RequestParam("id") Long id, @RequestParam("calificacion") int calificacion, Model model) {
        Tareas tarea = tareaService.obtenerTareaPorId(id.intValue());
        if (tarea == null) {
            model.addAttribute("error", "Tarea no encontrada.");
            return "redirect:/profesores/tareas/" + id;
        }

        tarea.setCalificacion_tarea(calificacion); // Corrected method name
        tareaService.actualizarTarea(tarea);
        return "redirect:/profesores/tareas/" + id;
    }


    //LISTAR TAREAS (trabajo de clase)
    @GetMapping("/asignatura/listarTareas/{idAsignatura}")
    public ModelAndView listarTareas(@PathVariable("idAsignatura") int idAsignatura) {
        ModelAndView mv = new ModelAndView();

        Asignaturas asignatura = asignaturaService.obtenerAsignaturaPorId(idAsignatura);
        mv.addObject("asignatura", asignatura);

        List<Tareas> tareas = tareaService.obtenerTareasPorAsignatura(idAsignatura);
        mv.addObject("tareas", tareas);

        mv.setViewName("pages/profesor/asignatura/listarTareaProfesor");
        return mv;
    }

    //AÑADIR TAREAS
    @GetMapping("/tareas/viewCrearTarea/{idAsignatura}")
    public ModelAndView mostrarPaginaAddTarea(@PathVariable("idAsignatura") int idAsignatura) {
        ModelAndView mv = new ModelAndView();

        Asignaturas asignatura = asignaturaService.obtenerAsignaturaPorId(idAsignatura);
        mv.addObject("asignatura", asignatura);

        mv.setViewName("pages/profesor/asignatura/anadirTareaProfesor");
        return mv;
    }

    @RequestMapping("/tareas/crearTarea/{idAsignatura}")
    public ModelAndView guardarTareas(@PathVariable("idAsignatura") int idAsignatura, @ModelAttribute Tareas tarea, @Validated BindingResult result) {
        ModelAndView mv = new ModelAndView();

        Asignaturas asignatura = asignaturaService.obtenerAsignaturaPorId(idAsignatura);
        mv.addObject("asignatura", asignatura);

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
            mv.setViewName("redirect:/profesor/tareas/viewCrearTarea/{idAsignatura}");
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
            mv.setViewName("redirect:/profesor/tareas/viewCrearTarea/{idAsignatura}");
            return mv;
        }
    }


}
