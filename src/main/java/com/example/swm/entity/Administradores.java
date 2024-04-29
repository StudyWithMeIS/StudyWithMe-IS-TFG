package com.example.swm.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;


@Entity
@NamedQuery(name = "Administrador.findAll", query = "SELECT a FROM Administradores a")
public class Administradores implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private int id_admin;

    private String nif_admin;
    private String nombre_admin;
    private String email_admin;
    private String password_admin;

    public Administradores() {}

    public Administradores(int id_admin, String nif_admin, String nombre_admin, String email_admin, String password_admin) {
        this.id_admin = id_admin;
        this.nif_admin = nif_admin;
        this.nombre_admin = nombre_admin;
        this.email_admin = email_admin;
        this.password_admin = password_admin;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public String getNif_admin() {
        return nif_admin;
    }

    public void setNif_admin(String nif_admin) {
        this.nif_admin = nif_admin;
    }

    public String getNombre_admin() {
        return nombre_admin;
    }

    public void setNombre_admin(String nombre_admin) {
        this.nombre_admin = nombre_admin;
    }

    public String getEmail_admin() {
        return email_admin;
    }

    public void setEmail_admin(String email_admin) {
        this.email_admin = email_admin;
    }

    public String getPassword_admin() {
        return password_admin;
    }

    public void setPassword_admin(String password_admin) {
        this.password_admin = password_admin;
    }


    @Override
    public String toString() {
        return "Administradores{" +
                "id_admin=" + id_admin +
                ", nif_admin='" + nif_admin + '\'' +
                ", nombre_admin='" + nombre_admin + '\'' +
                ", email_admin='" + email_admin + '\'' +
                ", password_admin='" + password_admin + '\'' +
                '}';
    }
}
