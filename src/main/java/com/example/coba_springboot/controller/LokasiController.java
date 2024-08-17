package com.example.coba_springboot.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.coba_springboot.entity.Lokasi;
import com.example.coba_springboot.exception.ResourceNotFoundException;
import com.example.coba_springboot.repository.LokasiRepository;

@RestController
@RequestMapping("/lokasi")
public class LokasiController {

    private final LokasiRepository lokasiRepository;

    public LokasiController(LokasiRepository lokasiRepository) {
        this.lokasiRepository = lokasiRepository;
    }

    @PostMapping
    public Lokasi createLokasi(@RequestBody Lokasi lokasi) {
        lokasi.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return lokasiRepository.save(lokasi);
    }

    @GetMapping
    public List<Lokasi> getAllLokasi() {
        return lokasiRepository.findAll();
    }

    @GetMapping("/{id}")
    public Lokasi getLokasiById(@PathVariable Long id) {
        return lokasiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lokasi not found with id " + id));
    }

    @PutMapping("/{id}")
    public Lokasi updateLokasi(@PathVariable Long id, @RequestBody Lokasi lokasiDetails) {
        Lokasi lokasi = lokasiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lokasi not found with id " + id));
        lokasi.setNamaLokasi(lokasiDetails.getNamaLokasi());
        lokasi.setNegara(lokasiDetails.getNegara());
        lokasi.setProvinsi(lokasiDetails.getProvinsi());
        lokasi.setKota(lokasiDetails.getKota());
        return lokasiRepository.save(lokasi);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLokasi(@PathVariable Long id) {
        Lokasi lokasi = lokasiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lokasi not found with id " + id));
        lokasiRepository.delete(lokasi);
        return ResponseEntity.ok().build();
    }
}
