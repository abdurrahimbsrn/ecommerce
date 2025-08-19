package com.ecommerce.kullanici_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "kullanici", schema = "kullanici_schema")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Kullanici {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kullaniciId;

    private String ad;
    private String soyad;
    private String telefon;

    @Column(nullable = false, unique = true)
    private String keycloakUserId;

    @ManyToOne(cascade = CascadeType.ALL)
    private Adres adres;
}
