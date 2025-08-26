package com.ecommerce.siparis_service.client;

import com.ecommerce.siparis_service.dto.UrunDto;
import com.ecommerce.siparis_service.client.TokenResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
public class UrunServiceClient {

    private final RestTemplate restTemplate;
    private final String urunServiceBaseUrl = "http://localhost:8084/urun";
    private final String keycloakTokenUrl = "http://localhost:8080/realms/your-realm/protocol/openid-connect/token";

    public UrunServiceClient() {
        this.restTemplate = new RestTemplate();

        // Token alma ve istek başlıklarına ekleme sorumluluğunu bir interceptor'e veriyoruz
        this.restTemplate.getInterceptors().add(new TokenInterceptor());
    }

    public UrunDto getUrunById(Long urunId) {
        // Interceptor sayesinde bu çağrıya token otomatik olarak eklenecek
        return restTemplate.getForObject(urunServiceBaseUrl + "/" + urunId, UrunDto.class);
    }

    // Diğer metotlar (örneğin stok güncelleme) buraya eklenebilir

    // REST Template için token alacak ve ekleyecek interceptor sınıfı
    private class TokenInterceptor implements ClientHttpRequestInterceptor {

        // Keycloak'ten alınacak token'ı ve bitiş süresini saklar
        private String cachedAccessToken;
        private long tokenExpirationTime;

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

            // Eğer token yoksa veya süresi dolduysa yenisini al
            if (cachedAccessToken == null || System.currentTimeMillis() > tokenExpirationTime) {
                cachedAccessToken = fetchNewToken();
            }

            // İsteğe Authorization başlığını ekle
            request.getHeaders().setBearerAuth(cachedAccessToken);

            return execution.execute(request, body);
        }

        private String fetchNewToken() {
            // Keycloak'e token almak için POST isteği yap
            MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
            requestBody.add("grant_type", "client_credentials");
            requestBody.add("client_id", "siparis-service-client"); // Keycloak'teki Client ID'niz
            requestBody.add("client_secret", "your-client-secret"); // Keycloak'teki Client Secret'iniz

            try {
                ResponseEntity<TokenResponse> tokenResponse = restTemplate.postForEntity(
                        keycloakTokenUrl, requestBody, TokenResponse.class);

                // Token'ı ve bitiş süresini kaydet
                this.tokenExpirationTime = System.currentTimeMillis() + (tokenResponse.getBody().getExpiresIn() * 1000) - 5000; // 5 saniye erken bitir
                return tokenResponse.getBody().getAccessToken();
            } catch (Exception e) {
                throw new RuntimeException("Keycloak'ten token alınamadı.", e);
            }
        }
    }
}