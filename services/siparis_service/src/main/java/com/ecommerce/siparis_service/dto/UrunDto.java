package com.ecommerce.siparis_service.dto;

import lombok.Data;

@Data
public class UrunDto {
    private Long id;
    private String ad;
    private Double fiyat;
    private Integer mevcutStok;
    private String aciklama;
}
