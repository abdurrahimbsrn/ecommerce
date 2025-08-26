package com.ecommerce.siparis_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="siparis", schema="siparis_schema")
@Builder
public class Siparis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long siparisId;

    private LocalDateTime olusturmaTarihi;

    private Double toplamFiyat;

    @Enumerated(EnumType.STRING) // Enum değerini string olarak saklar
    private SiparisDurumu siparisDurumu;
    private Long kullaniciId;

    @OneToOne(mappedBy = "siparis")
    private List<SiparisKalemleri> siparisKalemleri;

    @OneToOne(mappedBy = "siparis")
    private OdemeBilgileri odemeBilgileri;
}
