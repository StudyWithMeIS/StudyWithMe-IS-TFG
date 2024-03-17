package com.example.swm.repository;


import com.example.swm.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminInterface extends JpaRepository<Admin, String> {
}
