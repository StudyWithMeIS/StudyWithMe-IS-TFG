package swm.studywithmeistfg.classes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Padres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // ATRIBUTOS.
    private String nif_padres;
    private String nombre_padres;
    private String telefono_padres;
    private String email_padres;


    // CONSTRUCTOR

    public Padres() {}

    public Padres(String nif_padres, String nombre_padres, String telefono_padres, String email_padres) {
        this.nif_padres = nif_padres;
        this.nombre_padres = nombre_padres;
        this.telefono_padres = telefono_padres;
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

    public String getTelefono_padres() {
        return telefono_padres;
    }

    public void setTelefono_padres(String telefono_padres) {
        this.telefono_padres = telefono_padres;
    }

    public String getEmail_padres() {
        return email_padres;
    }

    public void setEmail_padres(String email_padres) {
        this.email_padres = email_padres;
    }



    // METODOS


    @Override
    public String toString() {
        return "Padres: NIF - " + nif_padres + '\'' + ", NOMBRES - " + nombre_padres + '\'' + ", TELEFONO - " + telefono_padres + '\'' + ", EMAIL - " + email_padres + '\'' + '}';
    }
}// CLOSE CLASS PADRES
