package com.caso_de_estudo_contingencia.estudo_contingencia.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.caso_de_estudo_contingencia.estudo_contingencia.exception.CustomException;

import io.jsonwebtoken.ExpiredJwtException;

public class JWTAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        try {

            Authentication authentication = TokenAuthenticationService.getAuthentication((HttpServletRequest) request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);

        } catch (ExpiredJwtException eje) {
            response.setContentType("Expired or invalid JWT token");
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw new CustomException("Unauthorized or invalid JWT token", HttpStatus.UNAUTHORIZED);
            // response.setContentType("Expired or invalid JWT token");
            // chain.doFilter(request, response);
        }

    }

}
