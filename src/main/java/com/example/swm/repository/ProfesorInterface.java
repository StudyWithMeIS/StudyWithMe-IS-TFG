package com.example.swm.repository;

import com.example.swm.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorInterface extends JpaRepository<Profesor, String> {
}
