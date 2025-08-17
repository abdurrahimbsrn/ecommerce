package com.ecommerce.siparis_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "siparis_kalemleri", schema = "siparis_schema")
@Data
public class SiparisKalemleri {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kalemId;
    private String urunAd;
    private Double urunFiyat;
    private Long urunId;
    private Integer miktar;
    @ManyToOne
    @JoinColumn(name = "siparisId")
    private Siparis siparis;
}
