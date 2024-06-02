package com.example.swm.repository;


import com.example.swm.entity.Asignaturas;
import com.example.swm.entity.TareaAsignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaAsignaturaRepository extends JpaRepository<TareaAsignatura, Integer> {





}
