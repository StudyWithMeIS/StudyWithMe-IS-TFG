package com.example.swm.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@NamedQuery(name="Profesor.findAll", query="SELECT p FROM Profesor p")
public class Profesor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String nif_profesor;
    private String nombre_profesor;
    private String email_profesor;
    private String password_profesor;

    public Profesor() {
    }

    public Profesor(String nif_profesor, String nombre_profesor, String email_profesor, String password_profesor) {
        this.nif_profesor = nif_profesor;
        this.nombre_profesor = nombre_profesor;
        this.email_profesor = email_profesor;
        this.password_profesor = password_profesor;
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
}
