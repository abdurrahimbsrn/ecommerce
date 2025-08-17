package com.ecommerce.siparis_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "odeme_bilgileri", schema = "siparis_schema")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OdemeBilgileri {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long odemeId;

    @OneToOne
    @JoinColumn(name = "siparisId")
    private Siparis siparis;

    @Enumerated(EnumType.STRING)
    private OdemeDurumu odemeDurumu;

    @Enumerated(EnumType.STRING)
    private OdemeYontemi odemeYontemi;
}

