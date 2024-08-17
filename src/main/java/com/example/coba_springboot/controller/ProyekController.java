package com.example.coba_springboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.coba_springboot.entity.Proyek;
import com.example.coba_springboot.exception.ResourceNotFoundException;
import com.example.coba_springboot.repository.LokasiRepository;
import com.example.coba_springboot.repository.ProyekRepository;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/proyek")
public class ProyekController {

    private final ProyekRepository proyekRepository;
    private final LokasiRepository lokasiRepository;

    public ProyekController(ProyekRepository proyekRepository, LokasiRepository lokasiRepository) {
        this.proyekRepository = proyekRepository;
        this.lokasiRepository = lokasiRepository;
    }

    @PostMapping
    public Proyek createProyek(@RequestBody Proyek proyek) {
        proyek.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        proyek.getLokasiList().forEach(lokasi -> {
            if (lokasi.getId() == null) {
                lokasiRepository.save(lokasi);
            }
        });
        return proyekRepository.save(proyek);
    }

    @GetMapping
    public List<Proyek> getAllProyek() {
        return proyekRepository.findAll();
    }

    @GetMapping("/{id}")
    public Proyek getProyekById(@PathVariable Long id) {
        return proyekRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyek not found with id " + id));
    }

    @PutMapping("/{id}")
    public Proyek updateProyek(@PathVariable Long id, @RequestBody Proyek proyekDetails) {
        Proyek proyek = proyekRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyek not found with id " + id));
        updateProyekDetails(proyek, proyekDetails);
        return proyekRepository.save(proyek);
    }

    private void updateProyekDetails(Proyek proyek, Proyek proyekDetails) {
        proyek.setNamaProyek(proyekDetails.getNamaProyek());
        proyek.setClient(proyekDetails.getClient());
        proyek.setTglMulai(proyekDetails.getTglMulai());
        proyek.setTglSelesai(proyekDetails.getTglSelesai());
        proyek.setPimpinanProyek(proyekDetails.getPimpinanProyek());
        proyek.setKeterangan(proyekDetails.getKeterangan());
        proyek.setLokasiList(proyekDetails.getLokasiList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProyek(@PathVariable Long id) {
        Proyek proyek = proyekRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyek not found with id " + id));
        proyekRepository.delete(proyek);
        return ResponseEntity.ok().build();
    }
}
