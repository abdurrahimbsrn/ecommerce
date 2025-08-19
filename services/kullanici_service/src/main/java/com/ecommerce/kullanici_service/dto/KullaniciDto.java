package com.ecommerce.kullanici_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class KullaniciDto {
    private Long id;
    private String ad;
    private String soyad;
    private String telefon;
}
