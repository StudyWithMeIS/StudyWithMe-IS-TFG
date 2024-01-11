package swm.studywithmeistfg.classes;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ATRIBUTOS.
    private String nif_administrador;
    private String nombre_administrador;
    private String telefono_administrador;
    private String email_administrador;



    // CONSTRUCTOR
    public Administrador() {}
    public Administrador(String nif_administrador, String nombre_administrador, String telefono_administrador, String email_administrador) {
        this.nif_administrador = nif_administrador;
        this.nombre_administrador = nombre_administrador;
        this.telefono_administrador = telefono_administrador;
        this.email_administrador = email_administrador;
    }




    // GETTERS & SETTERS
    public String getNif_administrador() {
        return nif_administrador;
    }

    public void setNif_administrador(String nif_administrador) {
        this.nif_administrador = nif_administrador;
    }

    public String getNombre_administrador() {
        return nombre_administrador;
    }

    public void setNombre_administrador(String nombre_administrador) {
        this.nombre_administrador = nombre_administrador;
    }

    public String getTelefono_administrador() {
        return telefono_administrador;
    }

    public void setTelefono_administrador(String telefono_administrador) {
        this.telefono_administrador = telefono_administrador;
    }

    public String getEmail_administrador() {
        return email_administrador;
    }

    public void setEmail_administrador(String email_administrador) {
        this.email_administrador = email_administrador;
    }




    // METODOS


    @Override
    public String toString() {
        return "Administrador: NIF - " + nif_administrador + '\'' + ", NOMBRE - " + nombre_administrador + '\'' + ", TELEFONO - " + telefono_administrador + '\'' + ", EMAIL - " + email_administrador + '\'' + '}';
    }
}//CLOSE CLASS ADMINISTRADOR
