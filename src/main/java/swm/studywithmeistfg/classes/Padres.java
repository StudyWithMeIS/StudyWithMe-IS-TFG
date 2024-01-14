package swm.studywithmeistfg.classes;

import jakarta.persistence.*;

@Entity
@Table
public class Padres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    // ATRIBUTOS - COLUMNAS
    @Column(name = "nif_padres", unique = true, nullable = false)
    private String nif_padres;

    @Column(name = "nombre_padres", nullable = false)
    private String nombre_padres;

    @Column(name = "edad_padres", nullable = false)
    private int edad_padres;

    @Column(name = "email_padres", nullable = false)
    private String email_padres;



    // CONSTRUCTORES
    public Padres(){}

    public Padres(String nif_padres, String nombre_padres, int edad_padres, String email_padres) {
        super();
        this.nif_padres = nif_padres;
        this.nombre_padres = nombre_padres;
        this.edad_padres = edad_padres;
        this.email_padres = email_padres;
    }


    // GETTERS & SETTERS
    public String getNif_padres() {
        return nif_padres;
    }

    public void setNif_padres(String nif_padres) {
        this.nif_padres = nif_padres;
    }

    public String getNombre_padres() {
        return nombre_padres;
    }

    public void setNombre_padres(String nombre_padres) {
        this.nombre_padres = nombre_padres;
    }

    public int getEdad_padres() {
        return edad_padres;
    }

    public void setEdad_padres(int edad_padres) {
        this.edad_padres = edad_padres;
    }

    public String getEmail_padres() {
        return email_padres;
    }

    public void setEmail_padres(String email_padres) {
        this.email_padres = email_padres;
    }

}// CLOSE CLASS PADRES
