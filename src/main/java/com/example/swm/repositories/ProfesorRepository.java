package com.example.swm.repositories;

import com.example.swm.entities.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    // Puedes agregar métodos personalizados si es necesario
}
