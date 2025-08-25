package com.ecommerce.urun_service.mapper;

import com.ecommerce.urun_service.dto.KategoriEkleDto;
import com.ecommerce.urun_service.dto.UrunDto;
import com.ecommerce.urun_service.dto.UrunEkleDto;
import com.ecommerce.urun_service.entity.Kategori;
import com.ecommerce.urun_service.entity.Urun;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UrunMapper {
    UrunDto toUrunDto(Urun urun);
    Urun toUrun(UrunDto urunDto);

    @Mapping(target = "kategori", ignore = true)
    Urun toUrun(UrunEkleDto urunEkleDto);

    //Urun toEntity(UrunEkleDto urunEkleDto);
    @Mapping(target = "kategori", ignore = true)
    void updateUrunFromDto(UrunEkleDto dto, @MappingTarget Urun urun);

    //Urun urunEkleDtoToUrun(UrunEkleDto urunEkleDto);
}
