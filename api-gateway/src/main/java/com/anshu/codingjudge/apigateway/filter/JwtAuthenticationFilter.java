package com.anshu.codingjudge.apigateway.filter;

import com.anshu.codingjudge.apigateway.util.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter
        implements Filter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(
            JwtUtil jwtUtil) {

        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest =
                (HttpServletRequest) request;

        HttpServletResponse httpResponse =
                (HttpServletResponse) response;

        String path =
                httpRequest.getRequestURI();

        if (path.startsWith("/api/auth")) {

            chain.doFilter(request, response);
            return;
        }

        String authHeader =
                httpRequest.getHeader("Authorization");

        if (authHeader == null ||
                !authHeader.startsWith("Bearer ")) {

            httpResponse.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED);

            return;
        }

        String token =
                authHeader.substring(7);

        if (!jwtUtil.isTokenValid(token)) {

            httpResponse.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED);

            return;
        }

        chain.doFilter(request, response);
    }
}