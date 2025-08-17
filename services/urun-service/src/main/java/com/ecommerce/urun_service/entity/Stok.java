package com.ecommerce.urun_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "stok_bilgileri",schema="urun_schema")
@Data
public class Stok {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long stokId;

    @NonNull
    private Integer mevcutStok;

    @OneToOne
    @JoinColumn(name="urunId")
    private Urun urun;
}
