package com.ecommerce.kullanici_service.controller;

import com.ecommerce.kullanici_service.dto.KullaniciDto;
import com.ecommerce.kullanici_service.dto.KullaniciEkleDto;
import com.ecommerce.kullanici_service.service.KullaniciService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kullanici")
public class KullaniciController {
    private final KullaniciService kullaniciService;

    @GetMapping
    //@PreAuthorize("hashRole('admin')")
    public List<KullaniciDto> getAllKullanici(){
        return kullaniciService.getAllKullanici();
    }
    @GetMapping("/{id}")
    public ResponseEntity<KullaniciDto> getAllKullaniciById(@PathVariable Long id){
        return kullaniciService.getKullaniciById(id);
    }
    @PostMapping
    public ResponseEntity<KullaniciDto> addKullanici(@RequestBody KullaniciEkleDto kullaniciEkleDto){
        return kullaniciService.addKullanici(kullaniciEkleDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<KullaniciDto> updateKullanici(@PathVariable Long id, @RequestBody KullaniciEkleDto kullaniciEkleDto){
        return kullaniciService.updateKullanici(id, kullaniciEkleDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        return kullaniciService.deleteKullanici(id);
    }
}
