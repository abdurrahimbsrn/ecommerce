package com.ecommerce.urun_service.repository;

import com.ecommerce.urun_service.entity.Urun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UrunRepository extends JpaRepository<Urun, Long> {

    @Query("SELECT u FROM Urun u JOIN u.kategori k WHERE k.id = :kategoriId")
    List<Urun> findByKategoriId(@Param("kategoriId") Long kategoriId);
}
