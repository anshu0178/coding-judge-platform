package com.anshu.codingjudge.submissionservice.dto;

import com.anshu.codingjudge.submissionservice.entity.SubmissionStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SubmissionResponse {

    private Long id;

    private Long problemId;

    private String userEmail;

    private String language;

    private SubmissionStatus status;

    private LocalDateTime submittedAt;

    public SubmissionResponse() {
    }

    // Generate getters and setters
}