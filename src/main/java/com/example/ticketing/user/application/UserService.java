package com.example.ticketing.user.application;

import com.example.ticketing.common.error.BusinessException;
import com.example.ticketing.common.error.ErrorCode;
import com.example.ticketing.user.api.SignupRequest;
import com.example.ticketing.user.api.SignupResponse;
import com.example.ticketing.user.domain.User;
import com.example.ticketing.user.domain.UserRole;
import com.example.ticketing.user.infrastructure.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public SignupResponse signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new BusinessException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        String passwordHash = passwordEncoder.encode(request.password());

        User user = new User(request.email(), passwordHash, UserRole.USER);
        User savedUser = userRepository.save(user);

        return SignupResponse.from(savedUser);
    }
}
