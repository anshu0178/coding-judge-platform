package com.anshu.codingjudge.authservice.service;

import com.anshu.codingjudge.authservice.dto.AuthResponse;
import com.anshu.codingjudge.authservice.dto.LoginRequest;
import com.anshu.codingjudge.authservice.dto.RegisterRequest;
import com.anshu.codingjudge.authservice.entity.User;

import java.util.List;

public interface AuthService {
    String registerUser(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    List<User> getAllUsers();
}
