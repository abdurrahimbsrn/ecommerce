package com.ecommerce.siparis_service.dto;

import com.ecommerce.siparis_service.entity.OdemeDurumu;
import com.ecommerce.siparis_service.entity.OdemeYontemi;
import lombok.Data;

@Data
public class OdemeEkleDto {
    private Long siparisId;
    private OdemeYontemi odemeYontemi;
}
