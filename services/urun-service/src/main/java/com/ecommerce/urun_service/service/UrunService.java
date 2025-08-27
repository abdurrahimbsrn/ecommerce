package com.ecommerce.urun_service.service;

import com.ecommerce.urun_service.dto.UrunDto;
import com.ecommerce.urun_service.dto.UrunEkleDto;
import com.ecommerce.urun_service.entity.Kategori;
import com.ecommerce.urun_service.entity.Urun;
import com.ecommerce.urun_service.mapper.UrunMapper;
import com.ecommerce.urun_service.repository.KategoriRepository;
import com.ecommerce.urun_service.repository.UrunRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class UrunService {

    private final UrunRepository urunRepository;
    private final KategoriRepository kategoriRepository;
    private final UrunMapper urunMapper;


    public ResponseEntity<UrunDto> getUrunById(Long id) {
        Optional<Urun> urunOptional = urunRepository.findById(id);

        return urunOptional.map(urun -> {
            return ResponseEntity.ok(urunMapper.toUrunDto(urun));
        }).orElse(ResponseEntity.notFound().build());
    }

    public List<UrunDto> getAllUruns() {
        List<Urun> urunList = urunRepository.findAll();
        List<UrunDto> urunDtoList = new ArrayList<>();
        for (Urun urun : urunList) {
            urunDtoList.add(urunMapper.toUrunDto(urun));
        }
        return urunDtoList;
    }


    public List<UrunDto> getUrunByKategori(Long kategorId) {
        List<Urun> urunList = urunRepository.findByKategoriId(kategorId);
        List<UrunDto> urunDtoList = new ArrayList<>();
        for (Urun urun : urunList) {
            urunDtoList.add(urunMapper.toUrunDto(urun));
        }
        return urunDtoList;
    }

    public UrunDto addUrun(UrunEkleDto urunEkleDto) {
        Kategori kategori = kategoriRepository.findById(urunEkleDto.getKategoriId())
                .orElseThrow(() -> new IllegalArgumentException("Kategori bulunamadı. ID: " + urunEkleDto.getKategoriId()));

        Urun urun = urunMapper.toUrun(urunEkleDto);
        urun.setKategori(kategori);

        Urun savedUrun = urunRepository.save(urun);
        return urunMapper.toUrunDto(savedUrun);

    }

    public ResponseEntity<UrunDto> updateUrun(Long id, UrunEkleDto urunDto) {

        Optional<Urun> urunOptional = urunRepository.findById(id);

        return urunOptional.map(urun -> {

            urunMapper.updateUrunFromDto(urunDto, urun);
            Urun updateUrun=urunRepository.save(urun);
            return ResponseEntity.ok(urunMapper.toUrunDto(urun));
        }).orElse(ResponseEntity.notFound().build());

    }

    public ResponseEntity<Void> deleteUrun(Long id) {
        if(!urunRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        urunRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Boolean> updateUrunStok(Long id, Integer stok) {
        Optional<Urun> urunOptional=urunRepository.findById(id);

        return urunOptional.map(urun -> {
            urun.setMevcutStok(urun.getMevcutStok()- stok);
            urunRepository.save(urun);
            return ResponseEntity.ok(true);
        }).orElse(ResponseEntity.notFound().build());
    }
}
