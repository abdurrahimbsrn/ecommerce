package com.ecommerce.siparis_service.controller;

import com.ecommerce.siparis_service.dto.SiparisDto;
import com.ecommerce.siparis_service.dto.SiparisEkleDto;
import com.ecommerce.siparis_service.service.SiparisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/siparis")
public class SiparisController {


    private final SiparisService siparisService;
    @PostMapping
    public ResponseEntity<SiparisDto> newSiparis(@RequestBody SiparisEkleDto siparisEkleDto){
        return ResponseEntity.ok(siparisService.newSiparis(SecurityContextHolder.getContext().getAuthentication(),siparisEkleDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<SiparisDto> getSiparisById(@PathVariable Long id){
       return ResponseEntity.ok(siparisService.getSiparisById(id));
    }
    @GetMapping("/kullanici")
    public List<SiparisDto> getSiparisByKullaniciId(@PathVariable Long id){
        return siparisService.getSiparisByKullaniciId(SecurityContextHolder.getContext().getAuthentication());
    }
    @GetMapping
    public List<SiparisDto> getAllSiparis(){
        return siparisService.getAllSiparis();
    }
    @PutMapping("/{id}/iptal")
    public ResponseEntity<SiparisDto> cancelSiparis(@PathVariable Long id){
        return siparisService.cancelSiparis(id);
    }

}
