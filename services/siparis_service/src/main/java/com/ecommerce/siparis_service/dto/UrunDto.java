package com.ecommerce.siparis_service.dto;

import lombok.Data;

@Data
public class UrunDto {
    private String urunAd;
    private Double fiyat;
    private Integer stok;
    private String aciklama;
    private Long kategori;
}
