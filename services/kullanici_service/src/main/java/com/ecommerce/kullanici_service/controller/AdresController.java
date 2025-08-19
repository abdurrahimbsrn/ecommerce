package com.ecommerce.kullanici_service.controller;

import com.ecommerce.kullanici_service.service.AdresService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/adres")
public class AdresController {
    private final AdresService adresService;

}
