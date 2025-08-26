package com.ecommerce.siparis_service.client;

import com.ecommerce.siparis_service.dto.UrunDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;


@Component
public class UrunServiceClient {

    private final RestTemplate restTemplate;
    private final String urunServiceBaseUrl = "http://localhost:8084/urun";

    @Autowired
    public UrunServiceClient(KeycloakTokenProvider keycloakTokenProvider) {
        this.restTemplate = new RestTemplate();

        this.restTemplate.getInterceptors().add(keycloakTokenProvider); // Interceptor ile istek atarken otomatik istek başlığına tokenı header olarak geçiyoru
    }

    public UrunDto getUrunById(Long urunId) {
        try {
            return restTemplate.getForObject(urunServiceBaseUrl + "/" + urunId, UrunDto.class);
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode().is4xxClientError()) {
                throw new RuntimeException("Ürün bulunamadı. ID: " + urunId, ex);
            }
            throw ex;
        } catch (HttpServerErrorException ex) {
            throw new RuntimeException("Ürün servisi ile iletişimde sunucu hatası oluştu.", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Ürün verisi alınırken beklenmeyen bir hata oluştu.", ex);
        }
    }

    public void stokDusur(Long urunId, Integer stok) {
        try {
            String url = urunServiceBaseUrl + "/stokDusur/" + urunId;

            HttpEntity<Integer> requestEntity = new HttpEntity<>(stok);

            restTemplate.put(url, requestEntity);

        } catch (HttpClientErrorException ex) {
            String errorMessage = "Stok düşürme işlemi başarısız. Hata kodu: " + ex.getStatusCode();

            if (ex.getStatusCode().is4xxClientError()) {
                errorMessage += ". Ürün bulunamadı veya istek hatalı.";
            }

            throw new RuntimeException(errorMessage, ex);

        } catch (HttpServerErrorException ex) {
            String errorMessage = "Ürün servisi ile iletişimde sunucu hatası oluştu. Hata kodu: " + ex.getStatusCode();
            throw new RuntimeException(errorMessage, ex);

        } catch (Exception ex) {
            throw new RuntimeException("Stok düşürme işlemi sırasında beklenmeyen bir hata oluştu.", ex);
        }
    }
}