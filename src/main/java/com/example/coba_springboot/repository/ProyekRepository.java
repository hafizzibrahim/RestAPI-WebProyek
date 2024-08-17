package com.example.coba_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.coba_springboot.entity.Proyek;

@Repository
public interface ProyekRepository extends JpaRepository<Proyek, Long> {
}

