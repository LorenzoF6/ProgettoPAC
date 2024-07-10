package com.emergency.webapp.repositories;


import com.emergency.webapp.models.Ospedale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OspedaleRepository extends JpaRepository<Ospedale, Integer> {
}