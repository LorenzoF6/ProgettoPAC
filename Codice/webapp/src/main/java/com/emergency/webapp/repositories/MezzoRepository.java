package com.emergency.webapp.repositories;

import com.emergency.webapp.models.Mezzo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MezzoRepository extends JpaRepository<Mezzo, Integer> {
}