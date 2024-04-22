package com.example.swm.repository;

import com.example.swm.entity.Tareas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareasRepository extends JpaRepository<Tareas, String> {
}
