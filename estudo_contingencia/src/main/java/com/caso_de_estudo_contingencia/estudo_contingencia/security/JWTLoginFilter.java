package com.caso_de_estudo_contingencia.estudo_contingencia.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.caso_de_estudo_contingencia.estudo_contingencia.util.DesEncrypterUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    public static final String username = "admin";
    public static final String password = "0DPiKuNIrrVmD8IUCuw1hQxNqZc="; // admin
    // static final String password = "212jCYsZ1wu1C9eYijoHo+tDpQI=";
    // //SistemaContingencia2@22

    public JWTLoginFilter(String url, AuthenticationManager authenticationManager, String profile) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        Authentication auth = null;

        try {
            AccountCredentials credentials = new ObjectMapper().readValue(request.getInputStream(),
                    AccountCredentials.class);

            if (validatePassword(credentials.getPassword(), password)) {
                return auth = new UsernamePasswordAuthenticationToken(credentials.getUsername(),
                        credentials.getPassword(),
                        Collections.emptyList());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getAuthenticationManager().authenticate(auth);
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain,
            Authentication auth) throws IOException, ServletException {

        TokenAuthenticationService.addAuthentication(response, auth.getName());
    }

    // Valida a password
    private boolean validatePassword(String inputPassword, String expectedPassword) {
        String senhaCrypto = DesEncrypterUtils.getInstance().encrypt(inputPassword);
        if (senhaCrypto.equals(expectedPassword))
            return true;
        else {
            return false;
        }
    }

    public static void main(String[] args) {
        try {
            String segredo = "#Sistema De Contingencia Segredo 26/07/2022 @";
            System.out.println(DesEncrypterUtils.getInstance().encrypt("SistemaContingencia2@22"));
            System.out.println(segredo.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
