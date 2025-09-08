package com.example.demo.repositories;

import com.example.demo.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    Optional<Offer> findByCodeAndActiveTrue(String code);
}
