package com.example.coba_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.coba_springboot.entity.Lokasi;

@Repository
public interface LokasiRepository extends JpaRepository<Lokasi, Long> {
}

