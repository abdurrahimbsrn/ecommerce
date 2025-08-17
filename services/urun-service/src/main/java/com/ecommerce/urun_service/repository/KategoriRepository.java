package com.ecommerce.urun_service.repository;


import com.ecommerce.urun_service.entity.Kategori;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KategoriRepository extends JpaRepository<Kategori,Long> {
}
