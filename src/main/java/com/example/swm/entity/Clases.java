package com.example.swm.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@NamedQuery(name="Clase.findAll", query="SELECT c FROM Clases c")
public class Clases implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id_clase;
    private String nombre_clase;

    public Clases() {
    }

    public Clases(String id_clase, String nombre_clase) {
        this.id_clase = id_clase;
        this.nombre_clase = nombre_clase;
    }

    public String getId_clase() {
        return id_clase;
    }

    public void setId_clase(String id_clase) {
        this.id_clase = id_clase;
    }

    public String getNombre_clase() {
        return nombre_clase;
    }

    public void setNombre_clase(String nombre_clase) {
        this.nombre_clase = nombre_clase;
    }

    @Override
    public String toString() {
        return "Clase{" +
                "id_clase='" + id_clase + '\'' +
                ", nombre_clase='" + nombre_clase + '\'' +
                '}';
    }
}
