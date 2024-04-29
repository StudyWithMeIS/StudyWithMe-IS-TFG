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
    @JoinColumn(name = "id_profesor_tarea")
    private Profesores profesor_tarea;

    @ManyToOne
    @JoinColumn(name = "id_alumno_tarea")
    private Alumnos alumno_tarea;

    @ManyToOne
    @JoinColumn(name = "id_asignatura_tarea")
    private Asignaturas asignatura_tarea;

    public Tareas() {}

    public Tareas(int id_tarea, String tipo_tarea, String titulo_tarea, String descripcion_tarea, double calificacion_tarea, Profesores profesor_tarea, Alumnos alumno_tarea, Asignaturas asignatura_tarea) {
        this.id_tarea = id_tarea;
        this.tipo_tarea = tipo_tarea;
        this.titulo_tarea = titulo_tarea;
        this.descripcion_tarea = descripcion_tarea;
        this.calificacion_tarea = calificacion_tarea;
        this.profesor_tarea = profesor_tarea;
        this.alumno_tarea = alumno_tarea;
        this.asignatura_tarea = asignatura_tarea;
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

    public Profesores getProfesor_tarea() {
        return profesor_tarea;
    }

    public void setProfesor_tarea(Profesores profesor_tarea) {
        this.profesor_tarea = profesor_tarea;
    }

    public Alumnos getAlumno_tarea() {
        return alumno_tarea;
    }

    public void setAlumno_tarea(Alumnos alumno_tarea) {
        this.alumno_tarea = alumno_tarea;
    }

    public Asignaturas getAsignatura_tarea() {
        return asignatura_tarea;
    }

    public void setAsignatura_tarea(Asignaturas asignatura_tarea) {
        this.asignatura_tarea = asignatura_tarea;
    }

    @Override
    public String toString() {
        return "Tareas{" +
                "id_tarea=" + id_tarea +
                ", tipo_tarea='" + tipo_tarea + '\'' +
                ", titulo_tarea='" + titulo_tarea + '\'' +
                ", descripcion_tarea='" + descripcion_tarea + '\'' +
                ", calificacion_tarea=" + calificacion_tarea +
                ", profesor=" + profesor_tarea +
                ", alumno=" + alumno_tarea +
                ", asignatura=" + asignatura_tarea +
                '}';
    }
}
