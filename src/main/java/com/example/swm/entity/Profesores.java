package com.example.swm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQuery(name = "Profesor.findAll", query = "SELECT p FROM Profesores p")
public class Profesores implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private int id_profesor;

    private String nif_profesor;
    private String nombre_profesor;
    private String email_profesor;
    private String password_profesor;

    @OneToMany(mappedBy = "profesores")
    private List<Asignaturas> asignaturas;

    public Profesores() {
    }

    public Profesores(int id_profesor, String nif_profesor, String nombre_profesor, String email_profesor, String password_profesor) {
        this.id_profesor = id_profesor;
        this.nif_profesor = nif_profesor;
        this.nombre_profesor = nombre_profesor;
        this.email_profesor = email_profesor;
        this.password_profesor = password_profesor;
    }

    public int getId_profesor() {
        return id_profesor;
    }

    public void setId_profesor(int id_profesor) {
        this.id_profesor = id_profesor;
    }

    public String getNif_profesor() {
        return nif_profesor;
    }

    public String getNombre_profesor() {
        return nombre_profesor;
    }

    public String getEmail_profesor() {
        return email_profesor;
    }

    public String getPassword_profesor() {
        return password_profesor;
    }

    public void setNif_profesor(String nif_profesor) {
        this.nif_profesor = nif_profesor;
    }

    public void setNombre_profesor(String nombre_profesor) {
        this.nombre_profesor = nombre_profesor;
    }

    public void setEmail_profesor(String email_profesor) {
        this.email_profesor = email_profesor;
    }

    public void setPassword_profesor(String password_profesor) {
        this.password_profesor = password_profesor;
    }

    public List<Asignaturas> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(List<Asignaturas> asignaturas) {
        this.asignaturas = asignaturas;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "id_profesor=" + id_profesor +
                ", nif_profesor='" + nif_profesor + '\'' +
                ", nombre_profesor='" + nombre_profesor + '\'' +
                ", email_profesor='" + email_profesor + '\'' +
                ", password_profesor='" + password_profesor + '\'' +
                ", asignaturas=" + asignaturas +
                '}';
    }
}