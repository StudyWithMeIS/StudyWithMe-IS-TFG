package com.example.swm.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tarea_asignatura")
public class TareaAsignatura implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarea_asignatura")
    private int idTareaAsignatura;

    @Column(name = "id_tarea")
    private int idTarea;

    @Column(name = "id_asignatura")
    private int idAsignatura;

    public TareaAsignatura() {}

    public TareaAsignatura(int idTareaAsignatura, int idTarea, int idAsignatura) {
        this.idTareaAsignatura = idTareaAsignatura;
        this.idTarea = idTarea;
        this.idAsignatura = idAsignatura;
    }

    public int getIdTareaAsignatura() {
        return idTareaAsignatura;
    }

    public void setIdTareaAsignatura(int idTareaAsignatura) {
        this.idTareaAsignatura = idTareaAsignatura;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public int getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    @Override
    public String toString() {
        return "TareaAsignatura{" +
                "idTareaAsignatura=" + idTareaAsignatura +
                ", idTarea=" + idTarea +
                ", idAsignatura=" + idAsignatura +
                '}';
    }
}
