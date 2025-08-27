package com.ecommerce.siparis_service.service;

import com.ecommerce.siparis_service.dto.SiparisDto;
import com.ecommerce.siparis_service.dto.SiparisEkleDto;
import com.ecommerce.siparis_service.dto.SiparisKalemEkleDto;
import com.ecommerce.siparis_service.dto.UrunDto;
import com.ecommerce.siparis_service.entity.*;
import com.ecommerce.siparis_service.mapper.SiparisMapper;
import com.ecommerce.siparis_service.repository.SiparisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.ecommerce.siparis_service.client.UrunServiceClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SiparisService {
    private final SiparisRepository siparisRepository;
    private final UrunServiceClient urunServiceClient;
    private final SiparisMapper siparisMapper;

    public SiparisDto newSiparis(Authentication authentication, SiparisEkleDto siparisEkleDto) {

        String uId=authentication.getName();

        List<SiparisKalemleri> siparisKalemleriList = new ArrayList<>();
        Double toplamFiyat = 0.0;

        for (SiparisKalemEkleDto kalemDto : siparisEkleDto.getSiparisKalemleri()) {

            UrunDto urunDto;
            urunDto = urunServiceClient.getUrunById(kalemDto.getUrunId());
            if(urunDto==null){
                throw new RuntimeException("Urun çekilemedi! : "+kalemDto.getUrunId());
            }
            if (urunDto.getMevcutStok()< kalemDto.getMiktar()) {
                throw new RuntimeException("Yetersiz stok: " + urunDto.getAd());
            }

            SiparisKalemleri siparisKalemi = new SiparisKalemleri();
            siparisKalemi.setMiktar(kalemDto.getMiktar());
            siparisKalemi.setUrunId(kalemDto.getUrunId());
            siparisKalemi.setUrunAd(urunDto.getAd());
            siparisKalemi.setUrunFiyat(urunDto.getFiyat());

            toplamFiyat += kalemDto.getMiktar() * urunDto.getFiyat();
            siparisKalemleriList.add(siparisKalemi);
        }

        SiparisModel siparisModel = new SiparisModel();
        siparisModel.setKullaniciId(uId);
        siparisModel.setOlusturmaTarihi(LocalDateTime.now());
        siparisModel.setToplamFiyat(toplamFiyat);
        siparisModel.setSiparisDurumu(SiparisDurumu.BEKLEMEDE);


        for (SiparisKalemleri kalem : siparisKalemleriList) {
            kalem.setSiparisModel(siparisModel);
        }
        siparisModel.setSiparisKalemleri(siparisKalemleriList);



        OdemeModel odemeModel =new OdemeModel();
        odemeModel.setOdemeDurumu(OdemeDurumu.BEKLEMEDE);
        odemeModel.setOdemeYontemi(OdemeYontemi.BELIRTILMEDI);
        siparisModel.setOdemeModel(odemeModel);
        odemeModel.setSiparisModel(siparisModel);


        SiparisModel kaydedilenSiparisModel = siparisRepository.save(siparisModel);

        // TODO ödeme tamamlandıktan sonra stok düşülsün.
        for (SiparisKalemleri kalem : siparisKalemleriList) {
            urunServiceClient.stokDusur(kalem.getUrunId(), kalem.getMiktar());
        }

        // Kaydedilen sipariş entity'sini yanıt DTO'suna çevir ve geri dön
        return siparisMapper.toDto(kaydedilenSiparisModel);
    }

    public SiparisDto getSiparisById(Long id) {
        Optional<SiparisModel> siparisModelOptional = siparisRepository.findById(id);
        return siparisModelOptional.map(siparisMapper::toDto).orElse(null);
    }

    public List<SiparisDto> getSiparisByKullaniciId(Authentication authentication) {

        String uId=authentication.getName();
        List<SiparisModel> siparisModelList = siparisRepository.findByKullaniciId(uId);
        return siparisModelList.stream().map(siparisMapper::toDto).collect(Collectors.toList());
    }

    public List<SiparisDto> getAllSiparis() {
        List<SiparisModel> siparisModelList = siparisRepository.findAll();
        return siparisModelList.stream().map(siparisMapper::toDto).collect(Collectors.toList());
    }

    public ResponseEntity<SiparisDto> cancelSiparis(Long id) {
        return null;
    }
}
