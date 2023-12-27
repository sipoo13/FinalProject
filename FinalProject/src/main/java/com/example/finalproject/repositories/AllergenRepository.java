package com.example.finalproject.repositories;

import com.example.finalproject.models.Allergen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergenRepository extends JpaRepository<Allergen, Long> {
}
