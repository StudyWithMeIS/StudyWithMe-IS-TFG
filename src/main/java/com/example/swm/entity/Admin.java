package com.example.swm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.Serial;
import java.io.Serializable;


@Entity
@NamedQuery(name = "Administrador.findAll", query = "SELECT admin FROM Admin admin")
public class Admin implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String nif_admin;
    private String nombre_admin;
    private String email_admin;
    private String password_admin;

    public Admin(String nif_admin, String nombre_admin, String email_admin, String password_admin) {
        super();
        this.nif_admin = nif_admin;
        this.nombre_admin = nombre_admin;
        this.email_admin = email_admin;
        this.password_admin = password_admin;
    }

    public Admin() {

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
        return "Admin{" +
                "nif_admin='" + nif_admin + '\'' +
                ", nombre_admin='" + nombre_admin + '\'' +
                ", email_admin='" + email_admin + '\'' +
                ", password_admin='" + password_admin + '\'' +
                '}';
    }
}
