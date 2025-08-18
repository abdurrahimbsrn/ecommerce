package com.ecommerce.siparis_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SiparisDto {
    private Date olusturmaTarihi;
    private Double toplamFiyat;
    private String siparisDurumu;
    private List<SiparisKalemDto> siparisKalemleri;
    private OdemeDto odemeDto;
}
