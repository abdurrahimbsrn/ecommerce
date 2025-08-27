package com.ecommerce.siparis_service.mapper;



import com.ecommerce.siparis_service.dto.SiparisDto;
import com.ecommerce.siparis_service.entity.SiparisModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = OdemeMapper.class)

public interface SiparisMapper {
    SiparisDto toDto(SiparisModel siparisModel);
}
