package com.example.finalproject.repositories;

import com.example.finalproject.models.FastFoodUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<FastFoodUser, Long> {
    FastFoodUser findFastFoodUserByLogin(String login);
}
