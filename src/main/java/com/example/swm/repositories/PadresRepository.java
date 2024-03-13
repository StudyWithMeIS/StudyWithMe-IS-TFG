package com.example.swm.repositories;

import com.example.swm.entities.Padres;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PadresRepository extends JpaRepository<Padres, Long> {
    // Puedes agregar métodos personalizados si es necesario
}
