package com.ecommerce.siparis_service.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ecommerce.siparis_service.repository.SiparisRepository;

@Service
@RequiredArgsConstructor
public class SiparisService {
    private final SiparisRepository siparisRepository;

}
