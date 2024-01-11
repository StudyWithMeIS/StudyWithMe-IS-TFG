package swm.studywithmeistfg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import swm.studywithmeistfg.classes.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    // Puedes agregar métodos personalizados si es necesario
}
