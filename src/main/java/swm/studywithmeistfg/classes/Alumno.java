package swm.studywithmeistfg.classes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Alumno {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ATRIBUTOS.
    private String nif_alumno;
    private String nombre_alumno;
    private int edad_alumno;
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



    // METODOS


    @Override
    public String toString() {
        return "Alumno: NIF - "+ nif_alumno + '\'' + ", NOMBRE - ='" + nombre_alumno + '\'' + ", EDAD - " + edad_alumno + ", EMAIL -" + email_alumno + '\'';
    }
}//CIERRA CLASS ALUMNO
