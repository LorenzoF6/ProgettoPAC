package com.emergency.webapp.securityConfig;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        System.out.println("AuthenticationSuccessHandler: User authenticated successfully\n\n");
        HttpSession session = request.getSession(false);
        if (session != null) {
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_OPERATORE"))) {
                session.setAttribute("Operatore", authentication.getName());
                System.out.println("\n\nSessione operatore118 con dati: "+session.getAttribute("Operatore"));
                response.sendRedirect("/api/emergenza/all");
            } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_VOLONTARIO"))) {
                session.setAttribute("Volontario", authentication.getName());
                System.out.println("\n\nSessione volontario con dati: "+session.getAttribute("Volontario"));
                response.sendRedirect("/api/volontari/infoVolontarioSquadra");
            } else {
                System.out.println("Errore: autenticazione fallita");
                response.sendRedirect("/login?error");
            }
        }
    }
}