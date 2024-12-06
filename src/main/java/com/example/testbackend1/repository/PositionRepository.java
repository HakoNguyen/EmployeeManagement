package com.example.testbackend1.repository;

import com.example.testbackend1.model.Position;
import com.example.testbackend1.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

<<<<<<< HEAD
public interface PositionRepository extends JpaRepository<Position, Long> {
    Optional<Position> findByPositionCode(String positionCode);
=======
@NonNullApi
public interface PositionRepository extends JpaRepository<Position, String> {
    Optional<Position> findById(String id);

    // tim chuc vu theo ma
    Position findByPositionCode(String positionCode);

    // Kiem tra su ton tai
>>>>>>> origin/main
    boolean existsByPositionCode(String positionCode);
    void deleteByPositionCode(String positionCode);
}