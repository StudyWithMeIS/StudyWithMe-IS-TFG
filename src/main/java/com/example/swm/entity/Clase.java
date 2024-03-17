package com.example.swm.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@NamedQuery(name="Clase.findAll", query="SELECT c FROM Clase c")
public class Clase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id_clase;
    private String nombre_clase;

    public Clase() {
    }

    public Clase(String id_clase, String nombre_clase) {
        super();
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
}
