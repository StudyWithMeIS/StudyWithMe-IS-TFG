package com.example.swm.entity;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "profesor_asignatura")
public class ProfesorAsignatura implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_profesor_asignatura;

    @ManyToOne
    @JoinColumn(name = "id_profesor", referencedColumnName = "id_profesor", nullable = false)
    private Profesores profesor;

    @ManyToOne
    @JoinColumn(name = "id_asignatura", referencedColumnName = "id_asignatura", nullable = false)
    private Asignaturas asignatura;

    public ProfesorAsignatura() {}

    public ProfesorAsignatura(Integer id_profesor_asignatura, Profesores profesor, Asignaturas asignatura) {
        this.id_profesor_asignatura = id_profesor_asignatura;
        this.profesor = profesor;
        this.asignatura = asignatura;
    }

    public Integer getId_profesor_asignatura() {
        return id_profesor_asignatura;
    }

    public void setId_profesor_asignatura(Integer id_profesor_asignatura) {
        this.id_profesor_asignatura = id_profesor_asignatura;
    }

    public Profesores getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesores profesor) {
        this.profesor = profesor;
    }

    public Asignaturas getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignaturas asignatura) {
        this.asignatura = asignatura;
    }

    @Override
    public String toString() {
        return "ProfesorAsignatura{" +
                "id_profesor_asignatura=" + id_profesor_asignatura +
                ", profesor=" + profesor +
                ", asignatura=" + asignatura +
                '}';
    }

}
