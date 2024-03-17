package com.example.swm.repository;

import com.example.swm.entity.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsignaturaInterface extends JpaRepository<Asignatura, String> {
}
