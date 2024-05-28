package com.example.swm.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "alumnos_tarea")
public class AlumnoTarea implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id_alumnos_tarea;

    @ManyToOne
    @JoinColumn(name = "id_alumno", referencedColumnName = "id_alumno")
    private Alumnos alumno;

    @ManyToOne
    @JoinColumn(name = "id_tarea", referencedColumnName = "id_tarea")
    private Tareas tarea;

    public AlumnoTarea() {}

    public AlumnoTarea(Integer id, Alumnos alumno, Tareas tarea) {
        this.alumno = alumno;
        this.tarea = tarea;
    }


    public Alumnos getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumnos alumno) {
        this.alumno = alumno;
    }

    public Tareas getTarea() {
        return tarea;
    }

    public void setTarea(Tareas tarea) {
        this.tarea = tarea;
    }

    @Override
    public String toString() {
        return "AlumnoTarea{" +
                ", alumno=" + alumno +
                ", tarea=" + tarea +
                '}';
    }
}
