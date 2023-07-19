package com.caso_de_estudo_contingencia.estudo_contingencia.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

    // EXPIRATION_TIME = 8 horas
    static final long EXPIRATION_TIME = 3600000;
    // static final long EXPIRATION_TIME = 300000;
    // static final String SECRET = "MySecret";
    static final String SECRET = "#Sistema9Gestao+De+Contingencia+Segredo+26/07/2022@";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";
    static final String RESPONSE_TIME_TITLE = "expires";
    static final String RESPONSE_TOKEN_TITLE = "tokenID";

    static Authentication getAuthentication(HttpServletRequest request) throws IOException {
        String token = request.getHeader(HEADER_STRING);

        // verificando se o token é nulo
        if (token != null) {
            String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJwt(token.replace(TOKEN_PREFIX, "")).getBody()
                    .getSubject();
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
            }
        }

        return null;
    }

    static void addAuthentication(HttpServletResponse response, String username) throws IOException {
        JSONObject resultToken = new JSONObject();
        String JWT = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        resultToken.put(RESPONSE_TOKEN_TITLE, JWT);
        resultToken.put(RESPONSE_TIME_TITLE, EXPIRATION_TIME);

        response.addHeader(HEADER_STRING, JWT);
        response.getWriter().write(resultToken.toString());
        // response.addHeader(RESPONSE_TIME_TITLE, Long.toString(EXPIRATION_TIME));
        // response.addHeader(RESPONSE_TOKEN_TITLE, JWT);
    }

}
