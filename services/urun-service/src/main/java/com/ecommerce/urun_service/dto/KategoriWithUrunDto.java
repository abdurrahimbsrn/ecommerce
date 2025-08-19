package com.ecommerce.urun_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class KategoriWithUrunDto {
    private String kategoriAd;
    private List<UrunDto> urunDto;
}
