package com.example.swm.repositories;

import com.example.swm.entities.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    // Puedes agregar métodos personalizados si es necesario
}
