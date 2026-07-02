package com.anshu.codingjudge.authservice.service;

import com.anshu.codingjudge.authservice.dto.AuthResponse;
import com.anshu.codingjudge.authservice.dto.LoginRequest;
import com.anshu.codingjudge.authservice.dto.RegisterRequest;
import com.anshu.codingjudge.authservice.entity.User;
import com.anshu.codingjudge.authservice.exception.InvalidCredentialsException;
import com.anshu.codingjudge.authservice.exception.UserAlreadyExistsException;
import com.anshu.codingjudge.authservice.exception.UserNotFoundException;
import com.anshu.codingjudge.authservice.repository.UserRepository;
import com.anshu.codingjudge.authservice.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public String registerUser(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        user.setRole("USER");

        userRepository.save(user);

        return "User Registered Successfully";

    }


    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(
                        () -> new UserNotFoundException("User not found")
                );

        boolean isPasswordValid =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if (!isPasswordValid) {
            throw new InvalidCredentialsException("Invalid Password");
        }

        String token =
                jwtService.generateToken(user.getEmail());

        return new AuthResponse(token);
    }
}
