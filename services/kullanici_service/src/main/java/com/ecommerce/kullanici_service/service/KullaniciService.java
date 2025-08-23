package com.ecommerce.kullanici_service.service;

import com.ecommerce.kullanici_service.dto.KullaniciDto;
import com.ecommerce.kullanici_service.dto.KullaniciEkleDto;
import com.ecommerce.kullanici_service.entity.Kullanici;
import com.ecommerce.kullanici_service.repository.KullaniciRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.OneToMany;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KullaniciService {


    private KullaniciDto createDto(Kullanici kullanici) {
        KullaniciDto kullaniciDto = new KullaniciDto();
        kullaniciDto.setAd(kullanici.getAd());
        kullaniciDto.setSoyad(kullanici.getSoyad());
        kullaniciDto.setTelefon(kullanici.getEmail());
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

    public ResponseEntity<KullaniciDto> getKullaniciById(Jwt jwt) {

        String keycloakId = jwt.getSubject();

        Kullanici kullanici = kullaniciRepository.findByKeycloakId(keycloakId)
                .orElseGet(() -> {
                    Kullanici yeni = new Kullanici();
                    yeni.setKeycloakId(keycloakId);
                    yeni.setEmail(jwt.getClaim("email"));
                    yeni.setAd(jwt.getClaim("given_name"));
                    yeni.setSoyad(jwt.getClaim("family_name"));
                    return kullaniciRepository.save(yeni);
                });
        return ResponseEntity.ok(createDto(kullanici));
    }

    public ResponseEntity<KullaniciDto> addKullanici(KullaniciEkleDto kullaniciEkleDto) {
        Kullanici kullanici = new Kullanici();
        kullanici.setAd(kullaniciEkleDto.getAd());
        kullanici.setEmail(kullaniciEkleDto.getTelefon());
        kullanici.setSoyad(kullaniciEkleDto.getSoyad());
        kullanici.setKeycloakId(kullaniciEkleDto.getKeycloakUserId());

        kullaniciRepository.save(kullanici);
        return ResponseEntity.ok(createDto(kullanici));
    }

    public ResponseEntity<KullaniciDto> updateKullanici(Long id, KullaniciEkleDto kullaniciEkleDto) {
        Optional<Kullanici> kullaniciOptional = kullaniciRepository.findById(id);
        return kullaniciOptional.map(kullanici -> {
            kullanici.setAd(kullaniciEkleDto.getAd());
            kullanici.setSoyad(kullaniciEkleDto.getSoyad());
            kullanici.setEmail(kullaniciEkleDto.getTelefon());

            return ResponseEntity.ok(createDto(kullanici));

        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> deleteKullanici(Long id) {
        if (kullaniciRepository.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        kullaniciRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
