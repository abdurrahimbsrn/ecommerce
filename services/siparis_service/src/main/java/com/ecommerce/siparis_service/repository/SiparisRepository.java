package com.ecommerce.siparis_service.repository;

import com.ecommerce.siparis_service.entity.SiparisModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SiparisRepository extends JpaRepository<SiparisModel, Long> {
    List<SiparisModel> findByKullaniciId(String kullaniciId);
}
