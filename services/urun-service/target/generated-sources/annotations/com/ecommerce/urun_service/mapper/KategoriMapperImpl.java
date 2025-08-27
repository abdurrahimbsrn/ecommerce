package com.ecommerce.urun_service.mapper;

import com.ecommerce.urun_service.dto.KategoriDto;
import com.ecommerce.urun_service.dto.KategoriEkleDto;
import com.ecommerce.urun_service.dto.KategoriWithUrunDto;
import com.ecommerce.urun_service.dto.UrunDto;
import com.ecommerce.urun_service.entity.Kategori;
import com.ecommerce.urun_service.entity.Urun;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-27T19:00:55+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Ubuntu)"
)
@Component
public class KategoriMapperImpl implements KategoriMapper {

    @Override
    public KategoriDto toKategoriDto(Kategori kategori) {
        if ( kategori == null ) {
            return null;
        }

        KategoriDto kategoriDto = new KategoriDto();

        kategoriDto.setId( kategori.getId() );
        kategoriDto.setKategoriAd( kategori.getKategoriAd() );
        kategoriDto.setAciklama( kategori.getAciklama() );
        kategoriDto.setEmoji( kategori.getEmoji() );

        return kategoriDto;
    }

    @Override
    public KategoriWithUrunDto toDtoWithUrunler(Kategori kategori) {
        if ( kategori == null ) {
            return null;
        }

        KategoriWithUrunDto kategoriWithUrunDto = new KategoriWithUrunDto();

        kategoriWithUrunDto.setKategoriAd( kategori.getKategoriAd() );
        kategoriWithUrunDto.setUrunDto( urunListToUrunDtoList( kategori.getUrunler() ) );

        return kategoriWithUrunDto;
    }

    @Override
    public Kategori toEntity(KategoriEkleDto kategoriEkleDto) {
        if ( kategoriEkleDto == null ) {
            return null;
        }

        Kategori.KategoriBuilder kategori = Kategori.builder();

        kategori.kategoriAd( kategoriEkleDto.getKategoriAd() );
        kategori.aciklama( kategoriEkleDto.getAciklama() );
        kategori.emoji( kategoriEkleDto.getEmoji() );

        return kategori.build();
    }

    @Override
    public void updateKategoriFromDto(KategoriEkleDto dto, Kategori kategori) {
        if ( dto == null ) {
            return;
        }

        kategori.setKategoriAd( dto.getKategoriAd() );
        kategori.setAciklama( dto.getAciklama() );
        kategori.setEmoji( dto.getEmoji() );
    }

    protected UrunDto urunToUrunDto(Urun urun) {
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

    protected List<UrunDto> urunListToUrunDtoList(List<Urun> list) {
        if ( list == null ) {
            return null;
        }

        List<UrunDto> list1 = new ArrayList<UrunDto>( list.size() );
        for ( Urun urun : list ) {
            list1.add( urunToUrunDto( urun ) );
        }

        return list1;
    }
}
