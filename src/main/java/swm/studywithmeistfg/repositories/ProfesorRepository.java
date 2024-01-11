package swm.studywithmeistfg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import swm.studywithmeistfg.classes.Profesor;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    // Puedes agregar métodos personalizados si es necesario
}
