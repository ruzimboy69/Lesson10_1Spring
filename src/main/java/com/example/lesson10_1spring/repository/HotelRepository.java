package com.example.lesson10_1spring.repository;

import com.example.lesson10_1spring.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel,Integer> {
    Boolean existsByName(String name);
    Optional<Hotel> findByName(String name);
}
