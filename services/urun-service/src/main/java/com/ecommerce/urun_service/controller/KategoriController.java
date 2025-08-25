package com.ecommerce.urun_service.controller;

import com.ecommerce.urun_service.dto.KategoriDto;
import com.ecommerce.urun_service.dto.KategoriEkleDto;
import com.ecommerce.urun_service.dto.KategoriWithUrunDto;
import com.ecommerce.urun_service.entity.Kategori;
import com.ecommerce.urun_service.service.KategoriService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kategori")
@RequiredArgsConstructor
public class KategoriController {

    private final KategoriService kategoriService;
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/all")
    public List<KategoriDto> getAllKategori(){
        return kategoriService.getAllKategori();
    }

    @GetMapping("/{id}")
    public ResponseEntity<KategoriWithUrunDto> getKategori(@PathVariable Long id){
        return kategoriService.getKategori(id);
    }

    @PreAuthorize("hasRole('admin')")
    @PutMapping("/{id}")
    public ResponseEntity<KategoriDto> updateKategori(@PathVariable Long id, @RequestBody KategoriEkleDto kategori){
        return kategoriService.updateKategori(id, kategori);
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKategori(@PathVariable Long id){
        return kategoriService.deleteKategori(id);
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/add")
    public ResponseEntity<Kategori> addKategori(@RequestBody KategoriEkleDto kategoriDto){
        return kategoriService.addKategori(kategoriDto);
    }
}
