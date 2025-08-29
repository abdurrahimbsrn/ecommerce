package com.ecommerce.kullanici_service.controller;

import com.ecommerce.kullanici_service.dto.AdresDto;
import com.ecommerce.kullanici_service.dto.AdresEkleDto;
import com.ecommerce.kullanici_service.entity.Adres;
import com.ecommerce.kullanici_service.service.AdresService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/adres")
public class AdresController {
    private final AdresService adresService;

    @GetMapping("/{id}")
    public ResponseEntity<AdresDto> getAdresById(@PathVariable Long id){
        return adresService.getAdresById(id);
    }
    @PostMapping("/kullanici/{id}")
    public ResponseEntity<AdresDto> addAdres(@PathVariable Long id, @RequestBody AdresEkleDto adresDto){
        return adresService.addAdres(id,adresDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AdresDto> updateAdres(@PathVariable Long id, @RequestBody AdresEkleDto adresEkleDto){
        return adresService.updateAdres(id,adresEkleDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdres(@PathVariable Long id){
        return adresService.deleteAdres(id);
    }
    @GetMapping("/kullanici/{id}")
    public ResponseEntity<Adres> getAdresByKeycloakId(@PathVariable String id){
        var adres=adresService.getAdresByKeycloakId(id);
        if(adres!=null){
            return ResponseEntity.ok(adres);
        }
        return ResponseEntity.notFound().build();
    }



}
