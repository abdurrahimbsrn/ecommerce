package com.ecommerce.urun_service.dto;

import lombok.Data;

@Data
public class UrunDto {
private Long urunId;
private String urunAd;
private Double fiyat;
private Integer stok;
private String aciklama;
private String kategori;
}
