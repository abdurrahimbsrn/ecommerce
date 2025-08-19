package com.ecommerce.kullanici_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdresEkleDto {
    private String adresAdi;
    private String ulke;
    private String sehir;
    private String ilce;
    private String detay;
}