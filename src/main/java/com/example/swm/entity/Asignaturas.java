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
    private String id_clase_asignatura;

    @Column(name = "nif_profesor", insertable = false, updatable = false)

    private String nif_profesor;

    public Asignaturas() {
    }

    @ManyToOne
    @JoinColumn(name = "nif_profesor")
    private Profesores profesores;

    public Asignaturas(String id_asignatura, String nombre_asignatura, String id_clase_asignatura, String nif_) {
        super();
        this.id_asignatura = id_asignatura;
        this.nombre_asignatura = nombre_asignatura;
        this.id_clase_asignatura = id_clase_asignatura;
        this.nif_profesor = nif_profesor;
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

    public String getId_clase_asignatura() {
        return id_clase_asignatura;
    }

    public void setId_clase_asignatura(String id_clase_asignatura) {
        this.id_clase_asignatura = id_clase_asignatura;
    }

    public String getNif_profesor() {
        return nif_profesor;
    }

    public void setNif_profesor(String nif_profesor) {
        this.nif_profesor = nif_profesor;
    }

    public Profesores getProfesor() {
        return profesores;
    }

    public void setProfesor(Profesores profesores) {
        this.profesores = profesores;
    }

    @Override
    public String toString() {
        return "Asignatura{" +
                "id_asignatura='" + id_asignatura + '\'' +
                ", nombre_asignatura='" + nombre_asignatura + '\'' +
                ", id_clase_asignatura='" + id_clase_asignatura + '\'' +
                ", nif_profesor='" + nif_profesor + '\'' +
                '}';
    }
}
