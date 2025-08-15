package com.ecommerce.urun_service.service;

import com.ecommerce.urun_service.dto.UrunDto;
import com.ecommerce.urun_service.entity.Urun;
import com.ecommerce.urun_service.repository.UrunRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class UrunService {

    private UrunRepository urunRepository;

    public ResponseEntity<UrunDto> getUrunById(Long id) {
        Optional<Urun> urunOptional = urunRepository.findById(id);

        return urunOptional.map(urun -> {
            UrunDto urunDto=new UrunDto();
            urunDto.setUrunId(urun.getId());
            urunDto.setUrunAd(urun.getAd());
            urunDto.setAciklama(urun.getAciklama());
            urunDto.setFiyat(urun.getFiyat());
            urunDto.setStok(urun.getStok().getMevcutStok());
            urunDto.setKategori(urun.getKategori().getKategoriAd());
            return ResponseEntity.ok(urunDto);
        }).orElse(ResponseEntity.notFound().build());
    }
    public List<UrunDto> getAllUruns(){
        List<Urun> urunList=urunRepository.findAll();
        List<UrunDto> urunDtoList=new ArrayList<>();
        for(Urun urun:urunList){
            UrunDto urunDto=new UrunDto();
            urunDto.setUrunId(urun.getId());
            urunDto.setUrunAd(urun.getAd());
            urunDto.setAciklama(urun.getAciklama());
            urunDto.setFiyat(urun.getFiyat());
            urunDto.setStok(urun.getStok().getMevcutStok());
            urunDto.setKategori(urun.getKategori().getKategoriAd());

            urunDtoList.add(urunDto);
        }
        return urunDtoList;
    }
}
