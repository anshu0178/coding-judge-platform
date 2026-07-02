package com.anshu.codingjudge.authservice.service;

import com.anshu.codingjudge.authservice.dto.AuthResponse;
import com.anshu.codingjudge.authservice.dto.LoginRequest;
import com.anshu.codingjudge.authservice.dto.RegisterRequest;

public interface AuthService {
    String registerUser(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
