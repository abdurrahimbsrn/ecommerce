package com.ecommerce.urun_service.dto;

import lombok.Data;

@Data
public class KategoriDto {
    private Long id;
    private String kategoriAd;
    private String aciklama;
    private String emoji;
}
