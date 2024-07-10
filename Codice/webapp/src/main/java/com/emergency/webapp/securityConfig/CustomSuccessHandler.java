package com.emergency.webapp.securityConfig;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
            System.out.println("AuthenticationSuccessHandler: User authenticated successfully");
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_OPERATORE"))) {
                response.sendRedirect("/api/emergenza/all");
            } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_VOLONTARIO"))) {
                response.sendRedirect("/api/volontari/infoVolontarioSquadra");
            } else {
                System.out.println("Errore: autenticazione fallita");
                response.sendRedirect("/login?error");
            }
        }
    }