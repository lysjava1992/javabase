package com.test.securtiy.repository;

import com.test.securtiy.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<CustomUser,Integer> {
    CustomUser findByUsername(String username);
}
