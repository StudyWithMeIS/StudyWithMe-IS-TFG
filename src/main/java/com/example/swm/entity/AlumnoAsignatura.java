package com.example.swm.entity;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "alumno_asignatura")
public class AlumnoAsignatura implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_alumno_asignatura;

    @ManyToOne
    @JoinColumn(name = "id_alumno", referencedColumnName = "id_alumno", nullable = false)
    private Alumnos alumno;

    @ManyToOne
    @JoinColumn(name = "id_asignatura", referencedColumnName = "id_asignatura", nullable = false)
    private Asignaturas asignatura;

    public AlumnoAsignatura() {}

    public AlumnoAsignatura(Integer id_alumno_asignatura, Alumnos alumno, Asignaturas asignatura) {
        this.id_alumno_asignatura = id_alumno_asignatura;
        this.alumno = alumno;
        this.asignatura = asignatura;
    }

    public Integer getId_alumno_asignatura() {
        return id_alumno_asignatura;
    }

    public void setId_alumno_asignatura(Integer id_alumno_asignatura) {
        this.id_alumno_asignatura = id_alumno_asignatura;
    }

    public Alumnos getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumnos alumno) {
        this.alumno = alumno;
    }

    public Asignaturas getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignaturas asignatura) {
        this.asignatura = asignatura;
    }

    @Override
    public String toString() {
        return "AlumnoAsignatura{" +
                "id_alumno_asignatura=" + id_alumno_asignatura +
                ", alumno=" + alumno +
                ", asignatura=" + asignatura +
                '}';
    }
}
