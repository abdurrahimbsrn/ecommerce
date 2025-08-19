package com.ecommerce.urun_service.service;

import com.ecommerce.urun_service.dto.KategoriDto;
import com.ecommerce.urun_service.dto.KategoriWithUrunDto;
import com.ecommerce.urun_service.dto.UrunDto;
import com.ecommerce.urun_service.entity.Kategori;
import com.ecommerce.urun_service.repository.KategoriRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KategoriService {
    private final KategoriRepository kategoriRepository;

    public List<Kategori> getAllKategori() {
        return kategoriRepository.findAll();
    }

    public ResponseEntity<KategoriWithUrunDto> getKategori(Long id) {
        Optional<Kategori> kategoriOptional = kategoriRepository.findById(id);
        return kategoriOptional.map(kategori -> {
            KategoriWithUrunDto kategoriDto=new KategoriWithUrunDto();
            kategoriDto.setKategoriAd(kategori.getKategoriAd());

            UrunDto
            kategoriDto.setUrunDto();

            return ResponseEntity.ok(kategori);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Kategori> updateKategori(Long id, KategoriDto kategoriDto) {
        Optional<Kategori> kategoriOptional = kategoriRepository.findById(id);
        return kategoriOptional.map(kategori -> {
            kategori.setKategoriAd(kategoriDto.getKategoriAd());
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

    public ResponseEntity<Kategori> addKategori(KategoriDto kategoriDto) {
        Kategori kategori=new Kategori();
        kategori.setKategoriAd(kategoriDto.getKategoriAd());
        kategoriRepository.save(kategori);
        return ResponseEntity.ok(kategori);
    }
}
