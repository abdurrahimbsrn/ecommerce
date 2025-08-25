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

    private  String ad;
    private String aciklama;
    private double fiyat;
    //private Date eklemeTarihi;

    @ManyToOne
    @JoinColumn(name = "kategoriId")
    private Kategori kategori;

    @NonNull
    private Integer mevcutStok;
}
