package com.emergency.webapp.repositories;

import com.emergency.webapp.models.Volontario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VolontarioRepository extends JpaRepository<Volontario, Integer> {
    Optional<Volontario> findByUserVolontario(String userVolontario);
}