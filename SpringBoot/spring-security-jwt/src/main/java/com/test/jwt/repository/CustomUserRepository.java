package com.test.jwt.repository;

import com.test.jwt.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CustomUserRepository extends JpaRepository<CustomUser, Integer> {
        CustomUser findByUsername(String username);
}
