package com.emergency.webapp.repositories;

import com.emergency.webapp.models.Operatore118;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface Operatore118Repository extends JpaRepository<Operatore118, String> {
    Optional<Operatore118> findByUserOperatore118(String userOperatore118);
}