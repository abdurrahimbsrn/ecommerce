package com.ecommerce.urun_service.mapper;

import com.ecommerce.urun_service.dto.UrunDto;
import com.ecommerce.urun_service.dto.UrunEkleDto;
import com.ecommerce.urun_service.entity.Urun;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-27T10:33:34+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class UrunMapperImpl implements UrunMapper {

    @Override
    public UrunDto toUrunDto(Urun urun) {
        if ( urun == null ) {
            return null;
        }

        UrunDto urunDto = new UrunDto();

        urunDto.setId( urun.getId() );
        urunDto.setAd( urun.getAd() );
        urunDto.setFiyat( urun.getFiyat() );
        urunDto.setMevcutStok( urun.getMevcutStok() );
        urunDto.setAciklama( urun.getAciklama() );

        return urunDto;
    }

    @Override
    public Urun toUrun(UrunDto urunDto) {
        if ( urunDto == null ) {
            return null;
        }

        Urun.UrunBuilder urun = Urun.builder();

        urun.id( urunDto.getId() );
        urun.ad( urunDto.getAd() );
        urun.aciklama( urunDto.getAciklama() );
        if ( urunDto.getFiyat() != null ) {
            urun.fiyat( urunDto.getFiyat() );
        }
        urun.mevcutStok( urunDto.getMevcutStok() );

        return urun.build();
    }

    @Override
    public Urun toUrun(UrunEkleDto urunEkleDto) {
        if ( urunEkleDto == null ) {
            return null;
        }

        Urun.UrunBuilder urun = Urun.builder();

        urun.ad( urunEkleDto.getAd() );
        urun.aciklama( urunEkleDto.getAciklama() );
        if ( urunEkleDto.getFiyat() != null ) {
            urun.fiyat( urunEkleDto.getFiyat() );
        }
        urun.mevcutStok( urunEkleDto.getMevcutStok() );

        return urun.build();
    }

    @Override
    public void updateUrunFromDto(UrunEkleDto dto, Urun urun) {
        if ( dto == null ) {
            return;
        }

        urun.setAd( dto.getAd() );
        urun.setAciklama( dto.getAciklama() );
        if ( dto.getFiyat() != null ) {
            urun.setFiyat( dto.getFiyat() );
        }
        urun.setMevcutStok( dto.getMevcutStok() );
    }
}
