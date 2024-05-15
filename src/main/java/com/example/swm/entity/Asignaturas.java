package com.example.swm.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;


@Entity
@NamedQuery(name = "Asignaturas.findAll", query = "SELECT a FROM Asignaturas a")
public class Asignaturas implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignatura")
    private int id_asignatura;

    @Column(name = "nombre_asignatura", nullable = false, unique = true)
    private String nombre_asignatura;

    @Column(name = "nombre_curso_asignatura", nullable = false, unique = true)
    private String nombre_curso_asignatura;

    @Column(name = "nif_profesor_asignatura", nullable = false, unique = true)
    private String nif_profesor_asignatura;

    public Asignaturas() {}

    public Asignaturas(int id_asignatura, String nombre_asignatura, String nombre_curso_asignatura, String nif_profesor_asignatura) {
        this.id_asignatura = id_asignatura;
        this.nombre_asignatura = nombre_asignatura;
        this.nombre_curso_asignatura = nombre_curso_asignatura;
        this.nif_profesor_asignatura = nif_profesor_asignatura;
    }

    public int getId_asignatura() {
        return id_asignatura;
    }

    public void setId_asignatura(int id_asignatura) {
        this.id_asignatura = id_asignatura;
    }

    public String getNombre_asignatura() {
        return nombre_asignatura;
    }

    public void setNombre_asignatura(String nombre_asignatura) {
        this.nombre_asignatura = nombre_asignatura;
    }

    public String getNombre_curso_asignatura() {
        return nombre_curso_asignatura;
    }

    public void setNombre_curso_asignatura(String nombre_curso_asignatura) {
        this.nombre_curso_asignatura = nombre_curso_asignatura;
    }

    public String getNif_profesor_asignatura() {
        return nif_profesor_asignatura;
    }

    public void setNif_profesor_asignatura(String nif_profesor_asignatura) {
        this.nif_profesor_asignatura = nif_profesor_asignatura;
    }

    @Override
    public String toString() {
        return "Asignaturas{" +
                "id_asignatura=" + id_asignatura +
                ", nombre_asignatura='" + nombre_asignatura + '\'' +
                ", nombre_curso_asignatura='" + nombre_curso_asignatura + '\'' +
                ", nif_profesor_asignatura='" + nif_profesor_asignatura + '\'' +
                '}';
    }
}