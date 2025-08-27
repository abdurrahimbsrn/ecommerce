package com.ecommerce.urun_service.dto;

import lombok.Data;

@Data
public class UrunEkleDto {
    private String ad;
    private Double fiyat;
    private Integer mevcutStok;
    private String aciklama;
    private Long kategoriId;
}
