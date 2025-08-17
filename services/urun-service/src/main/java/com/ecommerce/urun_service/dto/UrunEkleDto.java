package com.ecommerce.urun_service.dto;

import lombok.Data;

@Data
public class UrunEkleDto {
    private String urunAd;
    private Double fiyat;
    private Integer stok;
    private String aciklama;
    private Long kategori;
}
