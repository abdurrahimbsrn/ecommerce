package com.ecommerce.urun_service.mapper;

import com.ecommerce.urun_service.dto.UrunDto;
import com.ecommerce.urun_service.dto.UrunEkleDto;
import com.ecommerce.urun_service.entity.Urun;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-25T16:34:03+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Microsoft)"
)
@Component
public class UrunMapperImpl implements UrunMapper {

    @Override
    public UrunDto toUrunDto(Urun urun) {
        if ( urun == null ) {
            return null;
        }

        UrunDto urunDto = new UrunDto();

        urunDto.setFiyat( urun.getFiyat() );
        urunDto.setAciklama( urun.getAciklama() );

        return urunDto;
    }

    @Override
    public Urun toUrun(UrunDto urunDto) {
        if ( urunDto == null ) {
            return null;
        }

        Urun.UrunBuilder urun = Urun.builder();

        urun.aciklama( urunDto.getAciklama() );
        if ( urunDto.getFiyat() != null ) {
            urun.fiyat( urunDto.getFiyat() );
        }

        return urun.build();
    }

    @Override
    public Urun toUrun(UrunEkleDto urunEkleDto) {
        if ( urunEkleDto == null ) {
            return null;
        }

        Urun.UrunBuilder urun = Urun.builder();

        urun.aciklama( urunEkleDto.getAciklama() );
        if ( urunEkleDto.getFiyat() != null ) {
            urun.fiyat( urunEkleDto.getFiyat() );
        }

        return urun.build();
    }

    @Override
    public void updateUrunFromDto(UrunEkleDto dto, Urun urun) {
        if ( dto == null ) {
            return;
        }

        urun.setAciklama( dto.getAciklama() );
        if ( dto.getFiyat() != null ) {
            urun.setFiyat( dto.getFiyat() );
        }
    }
}
