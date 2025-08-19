package com.ecommerce.siparis_service.client;

import com.ecommerce.siparis_service.dto.UrunDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class UrunServiceClient {
    private final RestTemplate restTemplate;

    private final String url="http:localhost:8081/urun";

    public UrunDto getUrunById(Long id){
        return restTemplate.getForObject(url+"/"+id, UrunDto.class);
    }

}
