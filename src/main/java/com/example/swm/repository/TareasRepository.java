package com.example.swm.repository;

import com.example.swm.entity.Alumnos;
import com.example.swm.entity.Tareas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TareasRepository extends JpaRepository<Tareas, Integer> { }
