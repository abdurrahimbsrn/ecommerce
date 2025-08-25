package com.ecommerce.urun_service.service;

import com.ecommerce.urun_service.dto.KategoriDto;
import com.ecommerce.urun_service.dto.KategoriEkleDto;

import com.ecommerce.urun_service.dto.KategoriWithUrunDto;
import com.ecommerce.urun_service.entity.Kategori;
import com.ecommerce.urun_service.mapper.KategoriMapper;
import com.ecommerce.urun_service.repository.KategoriRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KategoriService {

    private final KategoriRepository kategoriRepository;

    private final KategoriMapper kategoriMapper;

    public List<KategoriDto> getAllKategori() {
        return kategoriRepository.findAll().stream().map(kategoriMapper::toKategoriDto).collect(Collectors.toList());
    }

    public ResponseEntity<KategoriWithUrunDto> getKategori(Long id) {

        Optional<Kategori> kategoriOptional = kategoriRepository.findById(id);
       if (kategoriOptional.isPresent()) {
            KategoriWithUrunDto dto = kategoriMapper.toDtoWithUrunler(kategoriOptional.get());

            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<KategoriDto> updateKategori(Long id, KategoriEkleDto kategoriEkleDto) {
        Optional<Kategori> kategoriOptional = kategoriRepository.findById(id);

        return kategoriOptional.map(kategori -> {

            kategoriMapper.updateKategoriFromDto(kategoriEkleDto,kategori);
            Kategori updateKategori=kategoriRepository.save(kategori);

            return ResponseEntity.ok(kategoriMapper.toKategoriDto(updateKategori));

        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> deleteKategori(Long id) {

        if (!kategoriRepository.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        kategoriRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Kategori> addKategori(KategoriEkleDto kategoriEkleDto) {
        Kategori kategori = kategoriMapper.toEntity(kategoriEkleDto);

        return ResponseEntity.ok(kategoriRepository.save(kategori));
    }
}
