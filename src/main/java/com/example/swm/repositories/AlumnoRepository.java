package com.example.swm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.swm.entities.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    // Puedes agregar métodos personalizados si es necesario
}
