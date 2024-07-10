package com.emergency.webapp.repositories;


import com.emergency.webapp.models.Squadra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SquadraRepository extends JpaRepository<Squadra, Integer> {
}