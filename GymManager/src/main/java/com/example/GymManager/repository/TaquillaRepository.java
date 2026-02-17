package com.example.GymManager.repository;

import com.example.GymManager.model.Taquilla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaquillaRepository extends JpaRepository<Taquilla, Long> {
}