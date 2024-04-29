package com.example.swm.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@NamedQuery(name = "Cursos.findAll", query = "SELECT c FROM Cursos c")
public class Cursos implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_curso;

    private String nombre_curso;

    public Cursos() {}

    public Cursos(Long id_curso, String nombre_curso) {
        this.id_curso = id_curso;
        this.nombre_curso = nombre_curso;
    }

    public Long getId_curso() {
        return id_curso;
    }

    public void setId_curso(Long id_curso) {
        this.id_curso = id_curso;
    }

    public String getNombre_curso() {
        return nombre_curso;
    }

    public void setNombre_curso(String nombre_curso) {
        this.nombre_curso = nombre_curso;
    }

    @Override
    public String toString() {
        return "Cursos{" +
                "id_curso=" + id_curso +
                ", nombre_curso='" + nombre_curso + '\'' +
                '}';
    }
}
