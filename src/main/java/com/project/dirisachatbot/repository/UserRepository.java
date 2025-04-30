package com.project.dirisachatbot.repository;


import com.project.dirisachatbot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Check if username exists
    Boolean existsByUsername(String username);

    // Check if email exists
    Boolean existsByEmail(String email);

    // Find user by username (for login)
    Optional<User> findByUsername(String username);

    // Find user by email (for password recovery)
    Optional<User> findByEmail(String email);

    // Custom query example: Find users with username containing string (case insensitive)
    // List<User> findByUsernameContainingIgnoreCase(String username);

    // Custom query example: Find enabled users
    // List<User> findByEnabledTrue();
}