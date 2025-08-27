package com.ecommerce.urun_service.mapper;

import com.ecommerce.urun_service.dto.KategoriDto;
import com.ecommerce.urun_service.dto.KategoriEkleDto;
import com.ecommerce.urun_service.dto.KategoriWithUrunDto;
import com.ecommerce.urun_service.entity.Kategori;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")

public interface KategoriMapper {
    @Mapping(source = "id", target = "id")
    KategoriDto toKategoriDto(Kategori kategori);

    @Mapping(source = "kategoriAd", target = "kategoriAd")
    @Mapping(source = "urunler", target = "urunDto")
    KategoriWithUrunDto toDtoWithUrunler(Kategori kategori);

    Kategori toEntity(KategoriEkleDto kategoriEkleDto);

    // Güncelleme metodu KategoriDto'dan gelen veriyi mevcut Kategori nesnesine kopyalar
    void updateKategoriFromDto(KategoriEkleDto dto, @MappingTarget Kategori kategori);
}