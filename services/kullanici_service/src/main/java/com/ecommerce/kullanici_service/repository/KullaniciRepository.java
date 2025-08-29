package com.ecommerce.kullanici_service.repository;

import com.ecommerce.kullanici_service.entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KullaniciRepository extends JpaRepository<Kullanici,Long> {
    Optional<Kullanici> findByKeycloakId(String keycloakId);
}
