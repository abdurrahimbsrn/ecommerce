package com.ecommerce.kullanici_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="adres", schema = "kullanici_schema")
@Builder
public class Adres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adresId;

    private String adresAdi;
    private String ulke;
    private String sehir;
    private String ilce;
    private String detay;

    @OneToOne
    @JoinColumn(name = "kullanici_id")
    private Kullanici kullanici;
}
