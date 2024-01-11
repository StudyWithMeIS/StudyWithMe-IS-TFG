package swm.studywithmeistfg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import swm.studywithmeistfg.classes.Padres;

public interface PadresRepository extends JpaRepository<Padres, Long> {
    // Puedes agregar métodos personalizados si es necesario
}
