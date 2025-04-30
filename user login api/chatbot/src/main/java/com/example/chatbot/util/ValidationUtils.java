package com.example.chatbot.util;

import com.example.chatbot.dto.LoginRequest;
import com.example.chatbot.dto.RegisterRequest;
import com.example.chatbot.exception.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class ValidationUtils {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public void validateRegisterRequest(RegisterRequest request) throws ValidationException {
        if (request == null) {
            throw new ValidationException("Request cannot be null");
        }
        validateEmail(request.getEmail());
        validatePassword(request.getPassword());
    }

    public void validateLoginRequest(LoginRequest request) throws ValidationException {
        if (request == null) {
            throw new ValidationException("Request cannot be null");
        }
        validateEmail(request.getEmail());
        validatePassword(request.getPassword());
    }

    private void validateEmail(String email) throws ValidationException {
        if (StringUtils.isBlank(email)) {
            throw new ValidationException("Email cannot be blank");
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new ValidationException("Invalid email format");
        }
        if (email.length() > 100) {
            throw new ValidationException("Email too long (max 100 chars)");
        }
    }

    private void validatePassword(String password) throws ValidationException {
        if (StringUtils.isBlank(password)) {
            throw new ValidationException("Password cannot be blank");
        }
        if (password.length() < 8) {
            throw new ValidationException("Password must be at least 8 characters");
        }
        if (password.length() > 50) {
            throw new ValidationException("Password too long (max 50 chars)");
        }
    }
}