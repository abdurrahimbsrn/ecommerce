package com.ecommerce.urun_service.controller;

import com.ecommerce.urun_service.dto.UrunDto;
import com.ecommerce.urun_service.dto.UrunEkleDto;
import com.ecommerce.urun_service.entity.Stok;


import com.ecommerce.urun_service.service.UrunService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.*;

import java.util.List;

@RestController
@RequestMapping("/urun")
@RequiredArgsConstructor
public class UrunController  {

    private final UrunService urunService;

    @GetMapping("/")
    public List<UrunDto> getAllUrun(){
        return urunService.getAllUruns();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UrunDto> getUrunById(@PathVariable Long id){  // PathVariable yol ile gelen değişkenleri metotla eşleştirir.
                                                                        // ResponseEntity Http yanıtlarını kontrol etmemizi sağlayan sınıf. Sadece veriyi değil cevabın kodunuda döner
        return urunService.getUrunById(id);
    }
    @PutMapping("/{id}/stok")
    public ResponseEntity<UrunDto> updateStok(@PathVariable Long id, @RequestBody Integer stok){
        return urunService.setUrunStok(id,stok);
    }
    @GetMapping("/kategori/{kategoriId}")
    public List<UrunDto> getUrunByKategoriId(@PathVariable Long kategorId){
        return urunService.getUrunByKategori(kategorId);
    }


    @PostMapping("/")
    public ResponseEntity<UrunDto> addUrun(@RequestBody UrunEkleDto urunEkleDto){
        return urunService.addUrun(urunEkleDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UrunDto> updateStok(@PathVariable Long id, @RequestBody UrunEkleDto urunEkleDto){
        return urunService.updateUrun(id,urunEkleDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUrun(@PathVariable Long id){
        return urunService.deleteUrun(id);
    }

}
