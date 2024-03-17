package com.example.swm.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@NamedQuery(name="Tarea.findAll", query="SELECT t FROM Tarea t")
public class Tarea implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id_tarea;
    private String tipo_tarea;
    private String titulo_tarea;
    private String descripcion_tarea;
    private String calificacion_tarea;
    private String nif_profesor_tarea;
    private String nif_alumno_calificacion;
    private String id_asignatura;

    public Tarea() {
    }

    public Tarea(String id_tarea, String tipo_tarea, String titulo_tarea, String descripcion_tarea, String calificacion_tarea, String nif_profesor_tarea, String nif_alumno_calificacion, String id_asignatura) {
        super();
        this.id_tarea = id_tarea;
        this.tipo_tarea = tipo_tarea;
        this.titulo_tarea = titulo_tarea;
        this.descripcion_tarea = descripcion_tarea;
        this.calificacion_tarea = calificacion_tarea;
        this.nif_profesor_tarea = nif_profesor_tarea;
        this.nif_alumno_calificacion = nif_alumno_calificacion;
        this.id_asignatura = id_asignatura;
    }

    public String getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(String id_tarea) {
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

    public String getCalificacion_tarea() {
        return calificacion_tarea;
    }

    public void setCalificacion_tarea(String calificacion_tarea) {
        this.calificacion_tarea = calificacion_tarea;
    }

    public String getNif_profesor_tarea() {
        return nif_profesor_tarea;
    }

    public void setNif_profesor_tarea(String nif_profesor_tarea) {
        this.nif_profesor_tarea = nif_profesor_tarea;
    }

    public String getNif_alumno_calificacion() {
        return nif_alumno_calificacion;
    }

    public void setNif_alumno_calificacion(String nif_alumno_calificacion) {
        this.nif_alumno_calificacion = nif_alumno_calificacion;
    }

    public String getId_asignatura() {
        return id_asignatura;
    }

    public void setId_asignatura(String id_asignatura) {
        this.id_asignatura = id_asignatura;
    }
}
