package com.ecommerce.urun_service.controller;

import com.ecommerce.urun_service.dto.UrunDto;
import com.ecommerce.urun_service.dto.UrunEkleDto;


import com.ecommerce.urun_service.service.UrunService;
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
    public List<UrunDto> getAllUrun() {
        return urunService.getAllUruns();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UrunDto> getUrunById(@PathVariable Long id) {  // PathVariable yol ile gelen değişkenleri metotla eşleştirir.
        // ResponseEntity Http yanıtlarını kontrol etmemizi sağlayan sınıf. Sadece veriyi değil cevabın kodunuda döner
        return urunService.getUrunById(id);
    }

    @GetMapping("/kategori/{id}")
    public List<UrunDto> getUrunByKategoriId(@PathVariable Long id) {
        return urunService.getUrunByKategori(id);
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping
    public ResponseEntity<UrunDto> addUrun(@RequestBody UrunEkleDto urunEkleDto) {
        UrunDto dto = urunService.addUrun(urunEkleDto);
        if (dto != null) {
            return ResponseEntity.ok(urunService.addUrun(urunEkleDto));
        }
        else{
            return ResponseEntity.notFound().build();
        }
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
    public ResponseEntity<Boolean> stokDusur(@PathVariable Long id, @RequestBody Integer stok){
        return urunService.updateUrunStok(id, stok);
    }
}
