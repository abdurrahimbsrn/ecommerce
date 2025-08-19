package com.ecommerce.kullanici_service.service;

import com.ecommerce.kullanici_service.repository.AdresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdresService {
    private final AdresRepository adresRepository;

}
