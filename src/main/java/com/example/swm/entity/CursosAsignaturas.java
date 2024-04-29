package com.example.swm.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@NamedQuery(name = "Cursos.findAll", query = "SELECT c FROM Cursos c")
public class CursosAsignaturas implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_curso;

    private int id_asignatura;

    public CursosAsignaturas() {}

    public CursosAsignaturas(int id_curso, int id_asignatura) {
        this.id_curso = id_curso;
        this.id_asignatura = id_asignatura;
    }

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public int getId_asignatura() {
        return id_asignatura;
    }

    public void setId_asignatura(int id_asignatura) {
        this.id_asignatura = id_asignatura;
    }

    @Override
    public String toString() {
        return "CursosAsignaturas{" +
                "id_curso=" + id_curso +
                ", id_asignatura=" + id_asignatura +
                '}';
    }
}
