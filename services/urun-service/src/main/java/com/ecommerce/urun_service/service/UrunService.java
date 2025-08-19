package com.ecommerce.urun_service.service;

import com.ecommerce.urun_service.dto.UrunDto;
import com.ecommerce.urun_service.dto.UrunEkleDto;
import com.ecommerce.urun_service.entity.Kategori;
import com.ecommerce.urun_service.entity.Stok;
import com.ecommerce.urun_service.entity.Urun;
import com.ecommerce.urun_service.repository.KategoriRepository;
import com.ecommerce.urun_service.repository.StokRepository;
import com.ecommerce.urun_service.repository.UrunRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class UrunService {

    private final UrunRepository urunRepository;
    private final KategoriRepository kategoriRepository;
    private final StokRepository stokRepository;

    private UrunDto createUrunDto(Urun urun) {
        UrunDto urunDto = new UrunDto();
        urunDto.setUrunId(urun.getId());
        urunDto.setUrunAd(urun.getAd());
        urunDto.setAciklama(urun.getAciklama());
        urunDto.setFiyat(urun.getFiyat());
        urunDto.setStok(urun.getStok().getMevcutStok());
        urunDto.setKategori(urun.getKategori().getKategoriAd());
        return urunDto;
    }

    public ResponseEntity<UrunDto> getUrunById(Long id) {
        Optional<Urun> urunOptional = urunRepository.findById(id);

        return urunOptional.map(urun -> {
            return ResponseEntity.ok(createUrunDto(urun));
        }).orElse(ResponseEntity.notFound().build());
    }

    public List<UrunDto> getAllUruns() {
        List<Urun> urunList = urunRepository.findAll();
        List<UrunDto> urunDtoList = new ArrayList<>();
        for (Urun urun : urunList) {
            urunDtoList.add(createUrunDto(urun));
        }
        return urunDtoList;
    }

    public ResponseEntity<UrunDto> setUrunStok(Long id, Integer newStok) {
        Optional<Urun> urunOptional = urunRepository.findById(id);
        return urunOptional.map(urun -> {
            Stok stok = urun.getStok();
            stok.setMevcutStok(newStok);
            urun.setStok(stok);

            return ResponseEntity.ok(createUrunDto(urun));
        }).orElse(ResponseEntity.notFound().build());
    }

    public List<UrunDto> getUrunByKategori(Long kategorId) {
        List<Urun> urunList = urunRepository.findByKategoriId(kategorId);
        List<UrunDto> urunDtoList = new ArrayList<>();
        for (Urun urun : urunList) {
            urunDtoList.add(createUrunDto(urun));
        }
        return urunDtoList;
    }

    public ResponseEntity<UrunDto> addUrun(UrunEkleDto urunDto) {
        Urun urun = new Urun();
        urun.setAd(urunDto.getUrunAd());
        urun.setAciklama(urunDto.getAciklama());
        urun.setFiyat(urunDto.getFiyat());

        Optional<Kategori> kategoriOptional = kategoriRepository.findById(urunDto.getKategori());

        if (kategoriOptional.isPresent()) { // ispresent ile mevcutluk kontrolü yapıyoruz
            urun.setKategori(kategoriOptional.get());

            Stok stok = new Stok();
            stok.setMevcutStok(urunDto.getStok());

            urun.setStok(stok);
            stok.setUrun(urun);
            urunRepository.save(urun);

            return ResponseEntity.ok(createUrunDto(urun));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<UrunDto> updateUrun(Long id, UrunEkleDto urunDto) {

        Optional<Urun> urunOptional = urunRepository.findById(id);

        if (urunOptional.isPresent()) {

            Urun urun = urunOptional.get();

            urun.setAd(urunDto.getUrunAd());
            urun.setAciklama(urunDto.getAciklama());
            urun.setFiyat(urunDto.getFiyat());

            Optional<Kategori> kategoriOptional = kategoriRepository.findById(urunDto.getKategori());

            if (kategoriOptional.isPresent()) { // ispresent ile mevcutluk kontrolü yapıyoruz
                urun.setKategori(kategoriOptional.get());

                urun.getStok().setMevcutStok(urunDto.getStok());
                urunRepository.save(urun);

                return ResponseEntity.ok(createUrunDto(urun));
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> deleteUrun(Long id) {
        if(!urunRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        urunRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
