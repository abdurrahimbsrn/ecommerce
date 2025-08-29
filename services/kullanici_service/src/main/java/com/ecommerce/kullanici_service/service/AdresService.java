package com.ecommerce.kullanici_service.service;

import com.ecommerce.kullanici_service.dto.AdresDto;
import com.ecommerce.kullanici_service.dto.AdresEkleDto;
import com.ecommerce.kullanici_service.dto.KullaniciDto;
import com.ecommerce.kullanici_service.entity.Adres;
import com.ecommerce.kullanici_service.entity.Kullanici;
import com.ecommerce.kullanici_service.repository.AdresRepository;
import com.ecommerce.kullanici_service.repository.KullaniciRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdresService {
    private final AdresRepository adresRepository;
    private final KullaniciRepository kullaniciRepository;

    private AdresDto createAdresDto(Adres adres) {
        AdresDto adresDto = new AdresDto();
        adresDto.setAdresId(adres.getAdresId());
        adresDto.setAdresAdi(adres.getAdresAdi());
        adresDto.setUlke(adres.getUlke());
        adresDto.setSehir(adres.getSehir());
        adresDto.setIlce(adres.getIlce());
        adresDto.setDetay(adres.getDetay());
        return adresDto;
    }

    private AdresEkleDto createAdresEkleDto(Adres adres) {
        AdresEkleDto adresEkleDto = new AdresEkleDto();
        adresEkleDto.setAdresAdi(adres.getAdresAdi());
        adresEkleDto.setUlke(adres.getUlke());
        adresEkleDto.setSehir(adres.getSehir());
        adresEkleDto.setIlce(adres.getIlce());
        adresEkleDto.setDetay(adres.getDetay());
        return adresEkleDto;
    }

    public ResponseEntity<AdresDto> getAdresById(Long id) {
        Optional<Adres> adresOptional = adresRepository.findById(id);
        return adresOptional.map(adres -> {
            return ResponseEntity.ok(createAdresDto(adres));
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<AdresDto> addAdres(Long id, AdresEkleDto adresDto) {
        Adres adres = new Adres();
        adres.setAdresAdi(adresDto.getAdresAdi());
        adres.setUlke(adresDto.getUlke());
        adres.setSehir(adresDto.getSehir());
        adres.setIlce(adresDto.getAdresAdi());
        adres.setDetay(adresDto.getDetay());

        adresRepository.save(adres);
        return ResponseEntity.ok(createAdresDto(adres));
    }

    public ResponseEntity<AdresDto> updateAdres(Long id, AdresEkleDto dto) {
        return adresRepository.findById(id)
                .map(adres -> {
                    adres.setAdresAdi(dto.getAdresAdi());
                    adres.setUlke(dto.getUlke());
                    adres.setSehir(dto.getSehir());
                    adres.setIlce(dto.getIlce());
                    adres.setDetay(dto.getDetay());

                    Adres updated = adresRepository.save(adres);


                    return ResponseEntity.ok(createAdresDto(updated));
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> deleteAdres(Long id) {
        if (adresRepository.findById(id).isPresent()) {
            adresRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    public List<AdresDto> getAdresByKullaniciId(Long id) {
        List<AdresDto> ada = new ArrayList<>();
        return ada;
    }

    public Adres getAdresByKeycloakId(String keycloakId) {
        //

        Optional<Kullanici> kullaniciOptional = kullaniciRepository.findByKeycloakId(keycloakId);

        return kullaniciOptional.map(Kullanici::getAdres).orElse(null);
    }


}
