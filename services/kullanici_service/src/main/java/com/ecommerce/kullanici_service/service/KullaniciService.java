package com.ecommerce.kullanici_service.service;

import com.ecommerce.kullanici_service.dto.KullaniciDto;
import com.ecommerce.kullanici_service.dto.KullaniciEkleDto;
import com.ecommerce.kullanici_service.entity.Kullanici;
import com.ecommerce.kullanici_service.repository.KullaniciRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.OneToMany;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KullaniciService {


    private KullaniciDto createDto(Kullanici kullanici){
        KullaniciDto kullaniciDto=new KullaniciDto();
        kullaniciDto.setAd(kullanici.getAd());
        kullaniciDto.setSoyad(kullanici.getSoyad());
        kullaniciDto.setTelefon(kullanici.getTelefon());
        kullaniciDto.setId(kullanici.getKullaniciId());
        return kullaniciDto;
    }

    private final KullaniciRepository kullaniciRepository;

    public List<KullaniciDto> getAllKullanici() {
        List<Kullanici> kullaniciList = kullaniciRepository.findAll();
        List<KullaniciDto> kullaniciDtoList = new ArrayList<>();
        for (Kullanici kullanici : kullaniciList) {
            kullaniciDtoList.add(createDto(kullanici));
        }
        return kullaniciDtoList;
    }

    public ResponseEntity<KullaniciDto> getKullaniciById(Long id) {
        Optional<Kullanici> kullaniciOptional=kullaniciRepository.findById(id);
        return kullaniciOptional.map(kullanici -> {
            return ResponseEntity.ok(createDto(kullanici));
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<KullaniciDto> addKullanici(KullaniciEkleDto kullaniciEkleDto) {
        Kullanici kullanici=new Kullanici();
        kullanici.setAd(kullaniciEkleDto.getAd());
        kullanici.setTelefon(kullaniciEkleDto.getTelefon());
        kullanici.setSoyad(kullaniciEkleDto.getSoyad());
        kullanici.setKeycloakUserId(kullaniciEkleDto.getKeycloakUserId());

        kullaniciRepository.save(kullanici);
        return ResponseEntity.ok(createDto(kullanici));
    }

    public ResponseEntity<KullaniciDto> updateKullanici(Long id, KullaniciEkleDto kullaniciEkleDto) {
        Optional<Kullanici> kullaniciOptional=kullaniciRepository.findById(id);
        return kullaniciOptional.map(kullanici -> {
            kullanici.setAd(kullaniciEkleDto.getAd());
            kullanici.setSoyad(kullaniciEkleDto.getSoyad());
            kullanici.setTelefon(kullaniciEkleDto.getTelefon());

            return ResponseEntity.ok(createDto(kullanici));

        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> deleteKullanici(Long id) {
        if(kullaniciRepository.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        kullaniciRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
