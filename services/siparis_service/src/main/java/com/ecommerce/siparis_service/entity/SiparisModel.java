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
public class SiparisModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long siparisId;

    private LocalDateTime olusturmaTarihi;

    private Double toplamFiyat;

    @Enumerated(EnumType.STRING) // Enum değerini string olarak saklar
    private SiparisDurumu siparisDurumu;
    private String kullaniciId;

    @OneToMany(mappedBy = "siparisModel", cascade = CascadeType.ALL)
    private List<SiparisKalemleri> siparisKalemleri;

    @OneToOne(mappedBy = "siparisModel", cascade = CascadeType.ALL)
    private OdemeModel odemeModel;

}
