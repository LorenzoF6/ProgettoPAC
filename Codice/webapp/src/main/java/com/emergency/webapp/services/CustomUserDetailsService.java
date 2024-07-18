package com.emergency.webapp.services;


import com.emergency.webapp.models.Operatore118;
import com.emergency.webapp.models.Volontario;
import com.emergency.webapp.repositories.Operatore118Repository;
import com.emergency.webapp.repositories.VolontarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private Operatore118Repository operatore118Repository;

    @Autowired
    private VolontarioRepository volontarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Operatore118> operatore = operatore118Repository.findByUserOperatore118(username);
        if (operatore.isPresent()) {
            User op118 = new User(operatore.get().getUserOperatore118(), "{noop}"+operatore.get().getPwdOperatore118(), getAuthority("ROLE_OPERATORE"));
            return op118;
        }

        Optional<Volontario> volontario = volontarioRepository.findByUserVolontario(username);
        if (volontario.isPresent()) {
            return new User(volontario.get().getUserVolontario(), "{noop}"+volontario.get().getPwdVolontario(), getAuthority("ROLE_VOLONTARIO"));
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }

    private List<SimpleGrantedAuthority> getAuthority(String role) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        System.out.println("\n\n");
        return authorities;
    }

}
