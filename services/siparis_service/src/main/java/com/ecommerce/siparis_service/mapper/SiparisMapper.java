package com.ecommerce.siparis_service.mapper;



import com.ecommerce.siparis_service.dto.SiparisDto;
import com.ecommerce.siparis_service.entity.Siparis;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SiparisMapper {
    SiparisDto toSiparisDto(Siparis siparis);
}
