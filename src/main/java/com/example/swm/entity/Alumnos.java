package com.example.swm.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQuery(name = "Alumnos.findAll", query = "SELECT a FROM Alumnos a")
public class Alumnos implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private int id_alumno;

    private String nif_alumno;

    private String nombre_alumno;

    private String email_alumno;

    private String password_alumno;

    private String nombre_padre_alumno;

    private String nombre_madre_alumno;

    @OneToMany(mappedBy = "alumnos", cascade = CascadeType.ALL)
    private List<Tareas> tareas;

    public Alumnos() {}

    public Alumnos(int id_alumno, String nif_alumno, String nombre_alumno, String email_alumno, String password_alumno, String nombre_padre_alumno, String nombre_madre_alumno, List<Tareas> tareas) {
        super();
        this.id_alumno = id_alumno;
        this.nif_alumno = nif_alumno;
        this.nombre_alumno = nombre_alumno;
        this.email_alumno = email_alumno;
        this.password_alumno = password_alumno;
        this.nombre_padre_alumno = nombre_padre_alumno;
        this.nombre_madre_alumno = nombre_madre_alumno;
        this.tareas = tareas;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public String getNif_alumno() {
        return nif_alumno;
    }

    public void setNif_alumno(String nif_alumno) {
        this.nif_alumno = nif_alumno;
    }

    public String getNombre_alumno() {
        return nombre_alumno;
    }

    public void setNombre_alumno(String nombre_alumno) {
        this.nombre_alumno = nombre_alumno;
    }

    public String getEmail_alumno() {
        return email_alumno;
    }

    public void setEmail_alumno(String email_alumno) {
        this.email_alumno = email_alumno;
    }

    public String getPassword_alumno() {
        return password_alumno;
    }

    public void setPassword_alumno(String password_alumno) {
        this.password_alumno = password_alumno;
    }

    public String getNombre_padre_alumno() {
        return nombre_padre_alumno;
    }

    public void setNombre_padre_alumno(String nombre_padre_alumno) {
        this.nombre_padre_alumno = nombre_padre_alumno;
    }

    public String getNombre_madre_alumno() {
        return nombre_madre_alumno;
    }

    public void setNombre_madre_alumno(String nombre_madre_alumno) {
        this.nombre_madre_alumno = nombre_madre_alumno;
    }

    public List<Tareas> getTareas() {
        return tareas;
    }

    public void setTareas(List<Tareas> tareas) {
        this.tareas = tareas;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id_alumno=" + id_alumno +
                ", nif_alumno='" + nif_alumno + '\'' +
                ", nombre_alumno='" + nombre_alumno + '\'' +
                ", email_alumno='" + email_alumno + '\'' +
                ", password_alumno='" + password_alumno + '\'' +
                ", nombre_padre_alumno='" + nombre_padre_alumno + '\'' +
                ", nombre_madre_alumno='" + nombre_madre_alumno + '\'' +
                '}';
    }
}
