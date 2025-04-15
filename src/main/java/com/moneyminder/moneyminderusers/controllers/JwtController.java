package com.moneyminder.moneyminderusers.controllers;

import com.nimbusds.jose.jwk.JWKSet;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class JwtController {

    private final JWKSet jwkSet;

    @GetMapping("/.well-known/jwks.json")
    public Map<String, Object> getJwks() {
        return this.jwkSet.toPublicJWKSet().toJSONObject();
    }
}
