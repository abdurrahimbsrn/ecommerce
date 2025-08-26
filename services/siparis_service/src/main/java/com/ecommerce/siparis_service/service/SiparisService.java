package com.ecommerce.siparis_service.service;

import com.ecommerce.siparis_service.dto.SiparisDto;
import com.ecommerce.siparis_service.dto.SiparisEkleDto;
import com.ecommerce.siparis_service.entity.Siparis;
import com.ecommerce.siparis_service.entity.SiparisDurumu;
import com.ecommerce.siparis_service.entity.SiparisKalemleri;
import com.ecommerce.siparis_service.repository.SiparisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ecommerce.siparis_service.client.UrunServiceClient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SiparisService {
    private final SiparisRepository siparisRepository;
    private final UrunServiceClient urunServiceClient;

    public ResponseEntity<SiparisEkleDto> newSiparis(SiparisEkleDto siparisEkleDto) {
        List<SiparisKalemleri> siparisKalemleriList=new ArrayList<>();
        Siparis siparis=new Siparis();
        siparis.setSiparisDurumu(SiparisDurumu.BEKLEMEDE);




        for (var siparisKalem: siparisEkleDto.getSiparisKalemleri()){

            var urunDto=urunServiceClient.getUrunById(siparisKalem.getUrunId());

            SiparisKalemleri siparisKalemleri=new SiparisKalemleri();

            siparisKalemleri.setMiktar(siparisKalem.getMiktar());
            siparisKalemleri.setUrunId(siparisKalem.getUrunId());
            siparisKalemleri.setUrunAd(urunDto.getUrunAd());
            siparisKalemleri.setUrunFiyat(urunDto.getFiyat());

            siparisKalemleriList.add(siparisKalemleri);
        }


    }

    public ResponseEntity<SiparisDto> getSiparisById(Long id) {
        return null;
    }

    public ResponseEntity<SiparisDto> getSiparisByKullaniciId(Long id) {
        return null;
    }

    public List<SiparisDto> getAllSiparis() {
        return null;
    }

    public ResponseEntity<SiparisDto> cancelSiparis(Long id) {
        return null;
    }
}
