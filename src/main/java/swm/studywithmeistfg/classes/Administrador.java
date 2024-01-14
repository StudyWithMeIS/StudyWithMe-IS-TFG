package swm.studywithmeistfg.classes;


import jakarta.persistence.*;

@Entity
@Table
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ATRIBUTOS.

    @Column(name = "nif_administrador", unique = true, nullable = false)
    private String nif_administrador;

    @Column(name = "nombre_administrador", nullable = false)
    private String nombre_administrador;

    @Column(name = "edad_administrador")
    private int edad_administrador;

    @Column(name = "email_administrador", nullable = false)
    private String email_administrador;



    // CONSTRUCTOR
    public Administrador() {}
    public Administrador(String nif_administrador, String nombre_administrador, int edad_administrador, String email_administrador) {
        this.nif_administrador = nif_administrador;
        this.nombre_administrador = nombre_administrador;
        this.edad_administrador = edad_administrador;
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

    public int getEdad_administrador() {
        return edad_administrador;
    }

    public void setTelefono_administrador(String telefono_administrador) {
        this.edad_administrador = edad_administrador;
    }

    public String getEmail_administrador() {
        return email_administrador;
    }

    public void setEmail_administrador(String email_administrador) {
        this.email_administrador = email_administrador;
    }

}//CLOSE CLASS ADMINISTRADOR
