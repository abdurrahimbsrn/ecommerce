package com.ecommerce.urun_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor // boş parametre almayan ctor olusturur
@AllArgsConstructor // tüm alanları parametre alarak bşr ctor oluşturur
@Table(name="kategoriler",schema="urun_schema")
@Builder
public class Kategori {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String kategoriAd;
    private String aciklama;
    private String emoji;

    @OneToMany(mappedBy = "kategori")
    private List<Urun> urunler;
}
