package com.example.chatbot.service;

import com.example.chatbot.dto.AuthResponse;
import com.example.chatbot.dto.LoginRequest;
import com.example.chatbot.dto.RegisterRequest;
import com.example.chatbot.exception.EmailAlreadyExistsException;
import com.example.chatbot.exception.InvalidCredentialsException;
import com.example.chatbot.exception.RegistrationException;
import com.example.chatbot.model.User;
import com.example.chatbot.repository.UserRepository;
import com.example.chatbot.security.JwtService;
import com.example.chatbot.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ValidationUtils validationUtils;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        try {
            validationUtils.validateRegisterRequest(request);

            if (userRepository.findByEmail(request.getEmail()).isPresent()) {
                throw new EmailAlreadyExistsException("Email already registered");
            }

            User user = User.builder()
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build();

            log.debug("Attempting to save user: {}", user.getEmail());
            User savedUser = userRepository.save(user);
            log.info("User registered successfully: {}", savedUser.getEmail());

            String token = jwtService.generateToken(savedUser);
            return new AuthResponse(token, savedUser.getEmail(), "Registration successful");

        } catch (DataAccessException e) {
            log.error("Database error during registration", e);
            throw new RegistrationException("Database operation failed");
        } catch (Exception e) {
            log.error("Unexpected error during registration", e);
            throw new RegistrationException("Registration failed: " + e.getMessage());
        }
    }

    public AuthResponse login(LoginRequest request) {
        try {
            validationUtils.validateLoginRequest(request);

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            User user = (User) authentication.getPrincipal();
            String token = jwtService.generateToken(user);

            return new AuthResponse(token, user.getEmail(), "Login successful");

        } catch (Exception e) {
            log.error("Login failed for email: {}", request.getEmail(), e);
            throw new InvalidCredentialsException("Invalid email or password");
        }
    }

}