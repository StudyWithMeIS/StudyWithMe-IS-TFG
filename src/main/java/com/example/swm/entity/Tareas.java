package com.example.swm.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@NamedQuery(name="Tarea.findAll", query="SELECT t FROM Tareas t")
public class Tareas implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_tarea;
    private String tipo_tarea;
    private String titulo_tarea;
    private String descripcion_tarea;
    private double calificacion_tarea;

    @ManyToOne
    @JoinColumn(name = "nif_profesor_tarea")
    private Profesores profesores;

    @ManyToOne
    @JoinColumn(name = "nif_alumno_calificacion")
    private Alumnos alumnos;

    @ManyToOne
    @JoinColumn(name = "id_asignatura")
    private Asignaturas asignaturas;

    public Tareas() {
    }

    public Tareas(int id_tarea, String tipo_tarea, String titulo_tarea, String descripcion_tarea, double calificacion_tarea, Profesores profesores, Alumnos alumnos, Asignaturas asignaturas) {
        this.id_tarea = id_tarea;
        this.tipo_tarea = tipo_tarea;
        this.titulo_tarea = titulo_tarea;
        this.descripcion_tarea = descripcion_tarea;
        this.calificacion_tarea = calificacion_tarea;
        this.profesores = profesores;
        this.alumnos = alumnos;
        this.asignaturas = asignaturas;
    }

    public int getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(int id_tarea) {
        this.id_tarea = id_tarea;
    }

    public String getTipo_tarea() {
        return tipo_tarea;
    }

    public void setTipo_tarea(String tipo_tarea) {
        this.tipo_tarea = tipo_tarea;
    }

    public String getTitulo_tarea() {
        return titulo_tarea;
    }

    public void setTitulo_tarea(String titulo_tarea) {
        this.titulo_tarea = titulo_tarea;
    }

    public String getDescripcion_tarea() {
        return descripcion_tarea;
    }

    public void setDescripcion_tarea(String descripcion_tarea) {
        this.descripcion_tarea = descripcion_tarea;
    }

    public double getCalificacion_tarea() {
        return calificacion_tarea;
    }

    public void setCalificacion_tarea(double calificacion_tarea) {
        this.calificacion_tarea = calificacion_tarea;
    }

    public Profesores getProfesor() {
        return profesores;
    }

    public void setProfesor(Profesores profesores) {
        this.profesores = profesores;
    }

    public Alumnos getAlumno() {
        return alumnos;
    }

    public void setAlumno(Alumnos alumnos) {
        this.alumnos = alumnos;
    }

    public Asignaturas getAsignatura() {
        return asignaturas;
    }

    public void setAsignatura(Asignaturas asignaturas) {
        this.asignaturas = asignaturas;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id_tarea=" + id_tarea +
                ", tipo_tarea='" + tipo_tarea + '\'' +
                ", titulo_tarea='" + titulo_tarea + '\'' +
                ", descripcion_tarea='" + descripcion_tarea + '\'' +
                ", calificacion_tarea=" + calificacion_tarea +
                ", profesor=" + profesores +
                ", alumno=" + alumnos +
                ", asignatura=" + asignaturas +
                '}';
    }
}
