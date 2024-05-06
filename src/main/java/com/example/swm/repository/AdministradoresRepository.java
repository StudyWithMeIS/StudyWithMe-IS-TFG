package com.example.swm.repository;


import com.example.swm.entity.Administradores;
import com.example.swm.entity.Alumnos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdministradoresRepository extends JpaRepository<Administradores, Integer> {
    @Query("SELECT a FROM Administradores a WHERE a.nif_admin = :nif_admin")
    Optional<Administradores> findAdministradorByNif(@Param("nif_admin") String nif_admin);
}
