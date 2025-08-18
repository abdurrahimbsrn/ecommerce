package com.ecommerce.siparis_service.client;

import jakarta.websocket.ClientEndpoint;
import org.hibernate.annotations.FetchProfile;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "urun-service", url = "http://localhost:8081")
@ClientEndpoint()
public class UrunClient {
}
