package com.ecommerce.kullanici_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class KullaniciAdresDto {
    private Long id;
    private String ad;
    private String soyad;
    private String telefon;
    private List<AdresDto> adres;
}
