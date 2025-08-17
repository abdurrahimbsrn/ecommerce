package com.ecommerce.urun_service.entity;

import lombok.*;
import jakarta.persistence.*;

import java.util.Date;

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

    @OneToOne(mappedBy = "urun", cascade=CascadeType.ALL)
    private Stok stok;
}
