package com.ecommerce.urun_service.dto;

import lombok.Data;

@Data
public class UrunWithKategoriDto {
    private Long id;
    private String ad;
    private Double fiyat;
    private Integer mevcutStok;
    private String aciklama;
    private String kategoriAd;
}
