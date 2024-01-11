package swm.studywithmeistfg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import swm.studywithmeistfg.classes.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    // Puedes agregar métodos personalizados si es necesario
}
