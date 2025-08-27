package com.ecommerce.siparis_service.controller;

import com.ecommerce.siparis_service.dto.OdemeDto;
import com.ecommerce.siparis_service.dto.OdemeEkleDto;
import com.ecommerce.siparis_service.service.OdemeService;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/odeme")
public class OdemeController {
    private final OdemeService odemeService;

    @GetMapping("/{id}")
    public OdemeDto getOdemeById(@PathVariable Long id){
        return odemeService.getOdemeById(id);
    }
    @PostMapping
    public ResponseEntity<OdemeDto> addOdeme(@RequestBody OdemeEkleDto odemeEkleDto ){
        return ResponseEntity.ok(odemeService.addOdeme(odemeEkleDto));
    }
}
