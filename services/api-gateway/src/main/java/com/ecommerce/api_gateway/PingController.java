package com.ecommerce.api_gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @GetMapping("/public/ping")
    public String ping() {
        return "pong";
    }
}