package com.ecommerce.urun_service.controller;

import com.ecommerce.urun_service.entity.Kategori;
import com.ecommerce.urun_service.service.KategoriService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/kategory")
@NoArgsConstructor
@AllArgsConstructor
public class KategoriController {

    KategoriService kategoriService;

    @GetMapping("/")
    public List<Kategori> getAllKategori(){
        return kategoriService.getAllKategori();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Kategori> getKategori(@PathVariable Long id){
        return kategoriService.getKategori(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Kategori> updateKategori(@PathVariable Long id, @RequestBody String kategoriName){
        return kategoriService.updateKategori(id,kategoriName);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKategori(@PathVariable Long id){
        return kategoriService.deleteKategori(id);
    }
    @PostMapping("/")
    public ResponseEntity<Kategori> addKategori(@RequestBody String kategoriAd){
        return kategoriService.addKategori(kategoriAd);
    }
}
