package swm.studywithmeistfg.classes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Profesor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // ATRIBUTOS.
    private String nif_profesor;
    private String nombre_profesor;
    private int edad_profesor;
    private String email_profesor;


    // CONSTRUCTOR


    public Profesor() {}

    public Profesor(String nif_profesor, String nombre_profesor, int edad_profesor, String email_profesor) {
        this.nif_profesor = nif_profesor;
        this.nombre_profesor = nombre_profesor;
        this.edad_profesor = edad_profesor;
        this.email_profesor = email_profesor;
    }

    // GETTERS & SETTERS


    public String getNif_profesor() {
        return nif_profesor;
    }

    public void setNif_profesor(String nif_profesor) {
        this.nif_profesor = nif_profesor;
    }

    public String getNombre_profesor() {
        return nombre_profesor;
    }

    public void setNombre_profesor(String nombre_profesor) {
        this.nombre_profesor = nombre_profesor;
    }

    public int getEdad_profesor() {
        return edad_profesor;
    }

    public void setEdad_profesor(int edad_profesor) {
        this.edad_profesor = edad_profesor;
    }

    public String getEmail_profesor() {
        return email_profesor;
    }

    public void setEmail_profesor(String email_profesor) {
        this.email_profesor = email_profesor;
    }

    // METODOS


    @Override
    public String toString() {
        return "Profesor: NIF - " + nif_profesor + '\'' + ", NOMBRE - " + nombre_profesor + '\'' + ", EDAD - " + edad_profesor + ", EMAIL - " + email_profesor + '\'';
    }
}//CLOSE CLASS PROFESOR
