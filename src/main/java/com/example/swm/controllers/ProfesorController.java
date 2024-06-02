package com.example.swm.controllers;

import com.example.swm.entity.Asignaturas;
import com.example.swm.entity.ProfesorAsignatura;
import com.example.swm.entity.Profesores;
import com.example.swm.entity.Tareas;
import com.example.swm.repository.ProfesoresRepository;
import com.example.swm.services.AsignaturaService;
import com.example.swm.services.ProfesorAsignaturaService;
import com.example.swm.services.TareaService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
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
        mv.addObject("profesor", PerfilProfersor(auth).getModel().get("profesor");
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
    @GetMapping("/profesor/{id}")
    public ModelAndView mostrarTablonClase(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView();
        Asignaturas asignatura = asignaturaService.obtenerAsignaturaPorId(id);
        mv.addObject("asignatura", asignatura);

        List<Tareas> tareas = tareaService.obtenerTareasPorAsignatura(id);
        mv.addObject("tareas", tareas);

        mv.setViewName("pages/profesor/asignatura/");
        return mv;
    }

}
