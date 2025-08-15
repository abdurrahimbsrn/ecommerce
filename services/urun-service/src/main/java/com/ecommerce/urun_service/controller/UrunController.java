package com.ecommerce.urun_service.controller;

import com.ecommerce.urun_service.dto.UrunDto;
import com.ecommerce.urun_service.entity.Stok;


import com.ecommerce.urun_service.service.UrunService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.*;

import java.util.List;

@RestController
@RequestMapping("/urun")
public class UrunController  {

    private final UrunService urunService;

    public UrunController(UrunService urunService) {
        this.urunService=urunService;
    }

    @GetMapping("/")
    public List<UrunDto> getAllUrun(){
        return urunService.getAllUruns();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UrunDto> getUrunById(@PathVariable Long id){  // PathVariable yol ile gelen değişkenleri metotla eşleştirir.
                                                                        // ResponseEntity Http yanıtlarını kontrol etmemizi sağlayan sınıf. Sadece veriyi değil cevabın kodunuda döner
        return urunService.getUrunById(id);
    }
}
