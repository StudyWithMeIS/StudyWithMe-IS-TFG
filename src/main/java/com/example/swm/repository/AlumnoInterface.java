package com.example.swm.repository;

import com.example.swm.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoInterface extends JpaRepository<Alumno, String> {
}
