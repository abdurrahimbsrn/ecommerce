package com.ecommerce.siparis_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SiparisKalemEkleDto {
    private Long urunId;
    private Integer miktar;
}
