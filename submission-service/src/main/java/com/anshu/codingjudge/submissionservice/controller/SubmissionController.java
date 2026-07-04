package com.anshu.codingjudge.submissionservice.controller;

import com.anshu.codingjudge.submissionservice.dto.CreateSubmissionRequest;
import com.anshu.codingjudge.submissionservice.dto.SubmissionResponse;
import com.anshu.codingjudge.submissionservice.security.JwtService;
import com.anshu.codingjudge.submissionservice.service.SubmissionService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    private final SubmissionService submissionService;
    private final JwtService jwtService;

    public SubmissionController(
            SubmissionService submissionService, JwtService jwtService) {

        this.submissionService = submissionService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public SubmissionResponse createSubmission(
            @Valid @RequestBody
            CreateSubmissionRequest request,
            Authentication authentication) {

        String email =
                authentication.getName();

        return submissionService.createSubmission(
                request,
                email
        );
    }

    @GetMapping
    public List<SubmissionResponse> getAllSubmissions() {

        return submissionService.getAllSubmissions();
    }

    @GetMapping("/{id}")
    public SubmissionResponse getSubmissionById(
            @PathVariable Long id) {

        return submissionService.getSubmissionById(id);
    }

    @GetMapping("/token-test")
    public String tokenTest(
            @RequestHeader("Authorization")
            String authHeader) {

        String token =
                authHeader.substring(7);

        return jwtService.extractUsername(token);
    }

    @GetMapping("/my-submissions")
    public List<SubmissionResponse>
    getMySubmissions(
            Authentication authentication) {

        String email =
                authentication.getName();

        return submissionService
                .getMySubmissions(email);
    }
}