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
    private String email;

    @Column(unique = true)
    private String keycloakId;

    @OneToOne(cascade = CascadeType.ALL)
    private Adres adres;
}
