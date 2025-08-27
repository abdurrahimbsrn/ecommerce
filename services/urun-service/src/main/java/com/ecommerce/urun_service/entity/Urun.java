package com.ecommerce.urun_service.entity;

import lombok.*;
import jakarta.persistence.*;


@Entity
@Table(name="urunler", schema="urun_schema")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Urun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private  String ad;
    @NonNull
    private String aciklama;
    @NonNull
    private double fiyat;
    //private Date eklemeTarihi;

    @ManyToOne
    @JoinColumn(name = "kategoriId")
    private Kategori kategori;

    @NonNull
    private Integer mevcutStok;
}
