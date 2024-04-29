package com.example.swm.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;


@Entity
@NamedQuery(name = "Asignaturas.findAll", query = "SELECT a FROM Asignaturas a")
public class Asignaturas implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id_asignatura;
    private String nombre_asignatura;

    @ManyToOne
    @JoinColumn(name = "id_curso_asignatura")
    private Cursos curso;

    @ManyToOne
    @JoinColumn(name = "nif_profesor")
    private Profesores profesores;

    public Asignaturas() {}

    public Asignaturas(String id_asignatura, String nombre_asignatura, Cursos curso, Profesores profesores) {
        this.id_asignatura = id_asignatura;
        this.nombre_asignatura = nombre_asignatura;
        this.curso = curso;
        this.profesores = profesores;
    }

    public String getId_asignatura() {
        return id_asignatura;
    }

    public void setId_asignatura(String id_asignatura) {
        this.id_asignatura = id_asignatura;
    }

    public String getNombre_asignatura() {
        return nombre_asignatura;
    }

    public void setNombre_asignatura(String nombre_asignatura) {
        this.nombre_asignatura = nombre_asignatura;
    }

    public Cursos getCurso() {
        return curso;
    }

    public void setCurso(Cursos curso) {
        this.curso = curso;
    }

    public Profesores getProfesores() {
        return profesores;
    }

    public void setProfesores(Profesores profesores) {
        this.profesores = profesores;
    }

    @Override
    public String toString() {
        return "Asignaturas{" +
                "id_asignatura='" + id_asignatura + '\'' +
                ", nombre_asignatura='" + nombre_asignatura + '\'' +
                ", curso=" + curso +
                ", profesores=" + profesores +
                '}';
    }
}
