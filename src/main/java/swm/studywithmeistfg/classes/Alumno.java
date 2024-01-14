package swm.studywithmeistfg.classes;

import jakarta.persistence.*;

@Entity
@Table
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ATRIBUTOS.

    @Column(name = "nif_alumno", unique = true, nullable = false)
    private String nif_alumno;

    @Column(name = "nombre_alumno", nullable = false)
    private String nombre_alumno;

    @Column(name = "edad_alumno", nullable = false)
    private int edad_alumno;

    @Column(name = "email_alumno", nullable = false)
    private String email_alumno;


    // CONSTRUCTOR.
    public Alumno() {}

    public Alumno(String nif_alumno, String nombre_alumno, int edad_alumno, String email_alumno) {
        this.nif_alumno = nif_alumno;
        this.nombre_alumno = nombre_alumno;
        this.edad_alumno = edad_alumno;
        this.email_alumno = email_alumno;
    }




    // GETTERS & SETTERS


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

    public int getEdad_alumno() {
        return edad_alumno;
    }

    public void setEdad_alumno(int edad_alumno) {
        this.edad_alumno = edad_alumno;
    }

    public String getEmail_alumno() {
        return email_alumno;
    }

    public void setEmail_alumno(String email_alumno) {
        this.email_alumno = email_alumno;
    }

}//CIERRA CLASS ALUMNO
