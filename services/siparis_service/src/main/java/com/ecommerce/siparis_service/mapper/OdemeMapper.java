package com.ecommerce.siparis_service.mapper;


import com.ecommerce.siparis_service.dto.OdemeDto;
import com.ecommerce.siparis_service.dto.OdemeEkleDto;
import com.ecommerce.siparis_service.entity.OdemeModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OdemeMapper {
    //@Mapping(target = "siparisId",ignore = true)
    //OdemeModel toEntity(OdemeEkleDto odemeEkleDto);

    OdemeDto toDto(OdemeModel odemeModel);
}
