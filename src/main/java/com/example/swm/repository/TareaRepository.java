package com.example.swm.repository;

import com.example.swm.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<Tarea, String> {
}
