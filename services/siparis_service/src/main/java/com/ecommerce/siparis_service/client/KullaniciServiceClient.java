package com.ecommerce.siparis_service.client;



import com.ecommerce.siparis_service.dto.UrunDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class KullaniciServiceClient {

    private final RestTemplate restTemplate;
    private final String urunServiceBaseUrl = "http://localhost:8082/kullanici";

    @Autowired
    public KullaniciServiceClient(KeycloakTokenProvider keycloakTokenProvider) {
        this.restTemplate = new RestTemplate();

        this.restTemplate.getInterceptors().add(keycloakTokenProvider); // Interceptor ile istek atarken otomatik istek başlığına tokenı header olarak geçiyoru
    }
// TODO silincek
    public UrunDto getKullaniciById(Long urunId) {
        return restTemplate.getForObject(urunServiceBaseUrl + "/" + urunId, UrunDto.class);
    }
}