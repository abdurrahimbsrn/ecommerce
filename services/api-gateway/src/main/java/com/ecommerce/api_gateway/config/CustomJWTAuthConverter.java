package com.ecommerce.api_gateway.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono; // Mono sınıfını import edin

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CustomJWTAuthConverter implements Converter<Jwt, Mono<AbstractAuthenticationToken>> {

    @Override
    public Mono<AbstractAuthenticationToken> convert(Jwt accessToken) {


        Map<String, Object> realmAccess = accessToken.getClaim("realm_access");
        List<String> rolesFromAccess = new ArrayList<>();
        if (realmAccess != null && realmAccess.containsKey("roles")) {
            rolesFromAccess = (List<String>) realmAccess.get("roles");
        }

        // Rolleri GrantedAuthority'ye dönüştürme
        Collection<GrantedAuthority> authorities = rolesFromAccess.stream()
                .map(roleName -> new SimpleGrantedAuthority("ROLE_" + roleName))
                .collect(Collectors.toList());

        // JwtAuthenticationToken oluşturma ve Mono içine sarma
        return Mono.just(new JwtAuthenticationToken(accessToken, authorities));
    }
}