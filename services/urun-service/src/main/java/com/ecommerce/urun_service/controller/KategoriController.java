package com.ecommerce.urun_service.controller;

import com.ecommerce.urun_service.dto.KategoriDto;
import com.ecommerce.urun_service.dto.KategoriWithUrunDto;
import com.ecommerce.urun_service.entity.Kategori;
import com.ecommerce.urun_service.service.KategoriService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kategori")
@RequiredArgsConstructor
public class KategoriController {

    private final KategoriService kategoriService;

    @GetMapping("/")
    public List<KategoriWithUrunDto> getAllKategori(){
        return kategoriService.getAllKategori();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Kategori> getKategori(@PathVariable Long id){
        return kategoriService.getKategori(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Kategori> updateKategori(@PathVariable Long id, @RequestBody KategoriDto kategori){
        return kategoriService.updateKategori(id, kategori);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKategori(@PathVariable Long id){
        return kategoriService.deleteKategori(id);
    }
    @PostMapping("/")
    public ResponseEntity<Kategori> addKategori(@RequestBody KategoriDto kategoriDto){
        return kategoriService.addKategori(kategoriDto);
    }
}
