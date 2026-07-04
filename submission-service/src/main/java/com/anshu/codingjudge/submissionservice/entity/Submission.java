package com.anshu.codingjudge.submissionservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "submissions")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long problemId;

    private String userEmail;

    private String language;

    @Column(columnDefinition = "TEXT")
    private String sourceCode;

    @Enumerated(EnumType.STRING)
    private SubmissionStatus status;

    private LocalDateTime submittedAt;

    public Submission() {
    }

    // Generate getters and setters
}