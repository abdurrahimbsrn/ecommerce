package com.ecommerce.siparis_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SiparisEkleDto {
    private Long kullaniciId;
    private List<SiparisKalemEkleDto> siparisKalemleri;
}
