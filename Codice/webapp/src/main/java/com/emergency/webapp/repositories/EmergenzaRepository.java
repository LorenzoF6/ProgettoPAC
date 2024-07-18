package com.emergency.webapp.repositories;

import com.emergency.webapp.models.Emergenza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergenzaRepository extends JpaRepository<Emergenza, Integer> {
}