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

    @Column(name = "id_profesor_tarea")
    private int id_profesor_tarea;

    @Column(name = "id_alumno_tarea")
    private int id_alumno_tarea;

    @Column(name = "id_asignatura_tarea")
    private int id_asignatura_tarea;

    public Tareas() {}

    public Tareas(int id_tarea, String tipo_tarea, String titulo_tarea, String descripcion_tarea, double calificacion_tarea, int id_profesor_tarea, int id_alumno_tarea, int id_asignatura_tarea) {
        this.id_tarea = id_tarea;
        this.tipo_tarea = tipo_tarea;
        this.titulo_tarea = titulo_tarea;
        this.descripcion_tarea = descripcion_tarea;
        this.calificacion_tarea = calificacion_tarea;
        this.id_profesor_tarea = id_profesor_tarea;
        this.id_alumno_tarea = id_alumno_tarea;
        this.id_asignatura_tarea = id_asignatura_tarea;
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

    public int getId_profesor_tarea() {
        return id_profesor_tarea;
    }

    public void setId_profesor_tarea(int id_profesor_tarea) {
        this.id_profesor_tarea = id_profesor_tarea;
    }

    public int getId_alumno_tarea() {
        return id_alumno_tarea;
    }

    public void setId_alumno_tarea(int id_alumno_tarea) {
        this.id_alumno_tarea = id_alumno_tarea;
    }

    public int getId_asignatura_tarea() {
        return id_asignatura_tarea;
    }

    public void setId_asignatura_tarea(int id_asignatura_tarea) {
        this.id_asignatura_tarea = id_asignatura_tarea;
    }

    @Override
    public String toString() {
        return "Tareas{" +
                "id_tarea=" + id_tarea +
                ", tipo_tarea='" + tipo_tarea + '\'' +
                ", titulo_tarea='" + titulo_tarea + '\'' +
                ", descripcion_tarea='" + descripcion_tarea + '\'' +
                ", calificacion_tarea=" + calificacion_tarea +
                ", profesor=" + id_profesor_tarea +
                ", alumno=" + id_alumno_tarea +
                ", asignatura=" + id_asignatura_tarea +
                '}';
    }
}
