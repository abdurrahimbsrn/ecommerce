package com.ecommerce.siparis_service.client;

import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
public class KeycloakTokenProvider implements ClientHttpRequestInterceptor {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String keycloakTokenUrl ="http://localhost:8080/realms/ecommerce/protocol/openid-connect/token";


    // Token ve bitiş süresini önbelleğe alacak değişkenler
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
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "client_credentials");
        requestBody.add("client_id", "backend-client");
        requestBody.add("client_secret", "89c6TquUdZu45p9P9j2nSWkwGHX7IBA3");

        try {
            ResponseEntity<TokenResponse> tokenResponse = restTemplate.postForEntity(
                    keycloakTokenUrl, requestBody, TokenResponse.class);

            if (tokenResponse.getBody() == null) {
                throw new RuntimeException("Keycloak'ten boş token yanıtı alındı.");
            }

            this.tokenExpirationTime = System.currentTimeMillis() + (tokenResponse.getBody().getExpiresIn() * 1000) - 5000;
            return tokenResponse.getBody().getAccessToken();
        } catch (Exception e) {
            throw new RuntimeException("Keycloak'ten token alınamadı. Hata: " + e.getMessage(), e);
        }
    }
}