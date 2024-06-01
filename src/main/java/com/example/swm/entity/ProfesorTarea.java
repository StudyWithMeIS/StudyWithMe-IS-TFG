package com.example.swm.entity;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "profesor_tarea")
public class ProfesorTarea implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id_profesor_tarea;

    @ManyToOne
    @JoinColumn(name = "id_profesor", referencedColumnName = "id_profesor")
    private Profesores profesor;

    @ManyToOne
    @JoinColumn(name = "id_tarea", referencedColumnName = "id_tarea")
    private Tareas tarea;

    public ProfesorTarea() {}

    public ProfesorTarea(Integer id, Profesores profesor, Tareas tarea) {
        this.profesor = profesor;
        this.tarea = tarea;
    }

    public Integer getId_profesor_tarea() {
        return id_profesor_tarea;
    }

    public void setId_profesor_tarea(Integer id_profesor_tarea) {
        this.id_profesor_tarea = id_profesor_tarea;
    }

    public Profesores getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesores profesor) {
        this.profesor = profesor;
    }

    public Tareas getTarea() {
        return tarea;
    }

    public void setTarea(Tareas tarea) {
        this.tarea = tarea;
    }

    @Override
    public String toString() {
        return "ProfesorTarea{" +
                ", profesor=" + profesor +
                ", tarea=" + tarea +
                '}';
    }
}
