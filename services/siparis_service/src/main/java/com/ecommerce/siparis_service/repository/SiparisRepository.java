package com.ecommerce.siparis_service.repository;

import com.ecommerce.siparis_service.entity.Siparis;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiparisRepository extends JpaRepository<Siparis, Long> {

}
