package com.ecommerce.kullanici_service.controller;

import com.ecommerce.kullanici_service.dto.KullaniciDto;
import com.ecommerce.kullanici_service.dto.KullaniciEkleDto;
import com.ecommerce.kullanici_service.service.KullaniciService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kullanici")
public class KullaniciController {
    private final KullaniciService kullaniciService;


    @GetMapping("/all")
    @PreAuthorize("hasRole('admin')")
    public List<KullaniciDto> getAllKullanici(){
        return kullaniciService.getAllKullanici();
    }
    @GetMapping
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<KullaniciDto> getAllKullaniciByToke(@AuthenticationPrincipal Jwt jwt){
        return kullaniciService.getKullaniciById(jwt);
    }
//    @PostMapping
//    public ResponseEntity<KullaniciDto> addKullanici(@RequestBody KullaniciEkleDto kullaniciEkleDto){
//        return kullaniciService.addKullanici(kullaniciEkleDto);
//    }
    @PutMapping("/{keycloakId}")
    public ResponseEntity<KullaniciDto> updateKullanici(@PathVariable String id, @RequestBody KullaniciEkleDto kullaniciEkleDto){
        return kullaniciService.updateKullanici(id, kullaniciEkleDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        return kullaniciService.deleteKullanici(id);
    }
}
