package com.ecommerce.urun_service.service;

import com.ecommerce.urun_service.entity.Kategori;
import com.ecommerce.urun_service.repository.KategoriRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class KategoriService {
    KategoriRepository kategoriRepository;

    public List<Kategori> getAllKategori() {
        return kategoriRepository.findAll();
    }

    public ResponseEntity<Kategori> getKategori(Long id) {
        Optional<Kategori> kategoriOptional = kategoriRepository.findById(id);
        return kategoriOptional.map(kategori -> {
            return ResponseEntity.ok(kategori);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Kategori> updateKategori(Long id, String kategoriName) {
        Optional<Kategori> kategoriOptional = kategoriRepository.findById(id);
        return kategoriOptional.map(kategori -> {
            kategori.setKategoriAd(kategoriName);
            return ResponseEntity.ok(kategori);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> deleteKategori(Long id) {

        if(!kategoriRepository.findById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        kategoriRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Kategori> addKategori(String kategoriAd) {
        Kategori kategori=new Kategori();
        kategori.setKategoriAd(kategoriAd);
        kategoriRepository.save(kategori);
        return ResponseEntity.ok(kategori);
    }
}
