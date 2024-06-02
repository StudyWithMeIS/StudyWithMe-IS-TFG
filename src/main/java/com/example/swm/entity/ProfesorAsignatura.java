package com.example.swm.entity;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "profesor_asignatura")
public class ProfesorAsignatura  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesor_asignatura")
    private int idProfesorAsignatura;

    @ManyToOne
    @JoinColumn(name = "id_profesor", referencedColumnName = "id_profesor")
    private Profesores profesor;

    @ManyToOne
    @JoinColumn(name = "id_asignatura", referencedColumnName = "id_asignatura")
    private Asignaturas asignatura;

    public ProfesorAsignatura() {}

    public ProfesorAsignatura(int idProfesorAsignatura, Profesores profesor, Asignaturas asignatura) {
        this.idProfesorAsignatura = idProfesorAsignatura;
        this.profesor = profesor;
        this.asignatura = asignatura;
    }

    public int getIdProfesorAsignatura() {
        return idProfesorAsignatura;
    }

    public void setIdProfesorAsignatura(int idProfesorAsignatura) {
        this.idProfesorAsignatura = idProfesorAsignatura;
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
                "idProfesorAsignatura=" + idProfesorAsignatura +
                ", profesor=" + profesor +
                ", asignatura=" + asignatura +
                '}';
    }
}
