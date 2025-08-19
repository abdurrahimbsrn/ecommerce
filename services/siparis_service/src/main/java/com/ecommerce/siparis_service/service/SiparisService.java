package com.ecommerce.siparis_service.service;

import com.ecommerce.siparis_service.dto.SiparisEkleDto;
import com.ecommerce.siparis_service.entity.Siparis;
import com.ecommerce.siparis_service.repository.SiparisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SiparisService {
    private final SiparisRepository siparisRepository;
    private final

    public ResponseEntity<SiparisEkleDto> newSiparis(SiparisEkleDto siparisEkleDto) {
        Siparis siparis=new Siparis();
        siparis.
    }
}
