package com.anshu.codingjudge.submissionservice.service;

import com.anshu.codingjudge.submissionservice.dto.CreateSubmissionRequest;
import com.anshu.codingjudge.submissionservice.dto.SubmissionResponse;

import java.util.List;

public interface SubmissionService {

    SubmissionResponse createSubmission(
            CreateSubmissionRequest request,
            String userEmail);

    List<SubmissionResponse> getMySubmissions(
            String userEmail);

    List<SubmissionResponse> getAllSubmissions();

    SubmissionResponse getSubmissionById(Long id);
}