package com.ecommerce.siparis_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SiparisKalemDto {
    private String urunAd;
    private Double urunFiyat;
    private Integer miktar;
}
