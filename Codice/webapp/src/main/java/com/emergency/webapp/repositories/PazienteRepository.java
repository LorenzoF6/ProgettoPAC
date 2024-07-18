package com.emergency.webapp.repositories;

import com.emergency.webapp.models.Paziente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PazienteRepository extends JpaRepository<Paziente, Integer> {
}