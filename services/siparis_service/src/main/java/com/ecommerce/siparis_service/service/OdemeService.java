package com.ecommerce.siparis_service.service;

import com.ecommerce.siparis_service.dto.OdemeDto;
import com.ecommerce.siparis_service.dto.OdemeEkleDto;
import com.ecommerce.siparis_service.entity.OdemeDurumu;
import com.ecommerce.siparis_service.entity.OdemeModel;
import com.ecommerce.siparis_service.entity.SiparisModel;
import com.ecommerce.siparis_service.mapper.OdemeMapper;
import com.ecommerce.siparis_service.repository.OdemeRepository;
import com.ecommerce.siparis_service.repository.SiparisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OdemeService {
    private final OdemeRepository odemeRepository;
    private final OdemeMapper odemeMapper;
    private final SiparisRepository siparisRepository;

    public OdemeDto addOdeme(OdemeEkleDto odemeEkleDto) {

        if (odemeEkleDto.getSiparisId() == null) {
            throw new IllegalArgumentException("Sipariş ID boş olamaz.");
        }

        SiparisModel siparis = siparisRepository.findById(odemeEkleDto.getSiparisId())
                .orElseThrow(() -> new IllegalArgumentException("Sipariş bulunamadı. ID: " + odemeEkleDto.getSiparisId()));

        OdemeModel odeme = siparis.getOdemeModel();
        odeme.setOdemeYontemi(odemeEkleDto.getOdemeYontemi());
        odeme.setOdemeDurumu(OdemeDurumu.BASARILI);

        odeme.setSiparisModel(siparis);
        siparis.setOdemeModel(odeme);
        OdemeModel savedOdeme = odemeRepository.save(odeme);

        return odemeMapper.toDto(savedOdeme);
    }

    public OdemeDto getOdemeById(Long id) {
        Optional<OdemeModel> odemeOptional = odemeRepository.findById(id);
        return odemeOptional.map(odemeMapper::toDto).orElse(null);
    }
}
