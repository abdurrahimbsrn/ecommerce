package com.ecommerce.siparis_service.service;

import com.ecommerce.siparis_service.dto.SiparisDto;
import com.ecommerce.siparis_service.dto.SiparisEkleDto;
import com.ecommerce.siparis_service.dto.SiparisKalemEkleDto;
import com.ecommerce.siparis_service.dto.UrunDto;
import com.ecommerce.siparis_service.entity.Siparis;
import com.ecommerce.siparis_service.entity.SiparisDurumu;
import com.ecommerce.siparis_service.entity.SiparisKalemleri;
import com.ecommerce.siparis_service.mapper.SiparisMapper;
import com.ecommerce.siparis_service.repository.SiparisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ecommerce.siparis_service.client.UrunServiceClient;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SiparisService {
    private final SiparisRepository siparisRepository;
    private final UrunServiceClient urunServiceClient;
    private final SiparisMapper siparisMapper;

    public SiparisDto newSiparis(SiparisEkleDto siparisEkleDto) {

        List<SiparisKalemleri> siparisKalemleriList = new ArrayList<>();
        Double toplamFiyat = 0.0;

        for (SiparisKalemEkleDto kalemDto : siparisEkleDto.getSiparisKalemleri()) {

            UrunDto urunDto;
            urunDto = urunServiceClient.getUrunById(kalemDto.getUrunId());
            if(urunDto==null){
                throw new RuntimeException("Urun çekilemedi! : "+kalemDto.getUrunId());
            }
            if (urunDto.getStok() < kalemDto.getMiktar()) {
                throw new RuntimeException("Yetersiz stok: " + urunDto.getUrunAd());
            }

            SiparisKalemleri siparisKalemi = new SiparisKalemleri();
            siparisKalemi.setMiktar(kalemDto.getMiktar());
            siparisKalemi.setUrunId(kalemDto.getUrunId());
            siparisKalemi.setUrunAd(urunDto.getUrunAd());
            siparisKalemi.setUrunFiyat(urunDto.getFiyat());

            toplamFiyat += kalemDto.getMiktar() * urunDto.getFiyat();
            siparisKalemleriList.add(siparisKalemi);
        }

        Siparis siparis = new Siparis();
        siparis.setKullaniciId(siparisEkleDto.getKullaniciId());
        siparis.setOlusturmaTarihi(LocalDateTime.now());
        siparis.setToplamFiyat(toplamFiyat);
        siparis.setSiparisDurumu(SiparisDurumu.BEKLEMEDE);


        for (SiparisKalemleri kalem : siparisKalemleriList) {
            kalem.setSiparis(siparis);
        }
        siparis.setSiparisKalemleri(siparisKalemleriList);

        Siparis kaydedilenSiparis = siparisRepository.save(siparis);

        for (SiparisKalemleri kalem : siparisKalemleriList) {
            urunServiceClient.stokDusur(kalem.getUrunId(), kalem.getMiktar());
        }

        // Kaydedilen sipariş entity'sini yanıt DTO'suna çevir ve geri dön
        return siparisMapper.toSiparisDto(kaydedilenSiparis);
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
