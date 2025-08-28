package com.ecommerce.urun_service.controller;

import com.ecommerce.urun_service.dto.UrunDto;
import com.ecommerce.urun_service.dto.UrunEkleDto;


import com.ecommerce.urun_service.dto.UrunWithKategoriDto;
import com.ecommerce.urun_service.entity.Urun;
import com.ecommerce.urun_service.service.UrunService;
import jakarta.annotation.Nullable;
import org.hibernate.dialect.unique.CreateTableUniqueDelegate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lombok.*;

import java.util.List;

@RestController
@RequestMapping("/urun")
@RequiredArgsConstructor
public class UrunController {

    private final UrunService urunService;

    @GetMapping("/all")
    public List<UrunWithKategoriDto> getAllUrun() {
        return urunService.getAllUruns();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UrunWithKategoriDto> getUrunById(@PathVariable Long id) {  // PathVariable yol ile gelen değişkenleri metotla eşleştirir.
        // ResponseEntity Http yanıtlarını kontrol etmemizi sağlayan sınıf. Sadece veriyi değil cevabın kodunuda döner
        //return urunService.getUrunById(id);
        var dto = urunService.getUrunById(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/kategori/{id}")
    public List<UrunDto> getUrunByKategoriId(@PathVariable Long id) {
        return urunService.getUrunByKategori(id);
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/add")
    public ResponseEntity<UrunDto> addUrun(@RequestBody UrunEkleDto urunEkleDto) {
        UrunDto newUrun = urunService.addUrun(urunEkleDto);
        return new ResponseEntity<>(newUrun, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('admin')")
    @PutMapping("/{id}")
    public ResponseEntity<UrunDto> updateUrun(@PathVariable Long id, @RequestBody UrunEkleDto urunEkleDto) {
        return urunService.updateUrun(id, urunEkleDto);
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUrun(@PathVariable Long id) {
        return urunService.deleteUrun(id);
    }

    @PreAuthorize("hasRole('admin')")
    @PutMapping("stokDusur/{id}")
    public ResponseEntity<Boolean> stokDusur(@PathVariable Long id, @RequestBody Integer stok) {
        return urunService.updateUrunStok(id, stok);
    }
}
