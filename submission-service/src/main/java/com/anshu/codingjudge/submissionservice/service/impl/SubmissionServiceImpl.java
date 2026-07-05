package com.anshu.codingjudge.submissionservice.service.impl;

import com.anshu.codingjudge.submissionservice.dto.CreateSubmissionRequest;
import com.anshu.codingjudge.submissionservice.dto.SubmissionResponse;
import com.anshu.codingjudge.submissionservice.dto.judge.JudgeRequest;
import com.anshu.codingjudge.submissionservice.dto.judge.JudgeResponse;
import com.anshu.codingjudge.submissionservice.entity.Submission;
import com.anshu.codingjudge.submissionservice.entity.SubmissionStatus;
import com.anshu.codingjudge.submissionservice.exception.SubmissionNotFoundException;
import com.anshu.codingjudge.submissionservice.repository.SubmissionRepository;
import com.anshu.codingjudge.submissionservice.service.SubmissionService;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class SubmissionServiceImpl implements SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final RestTemplate restTemplate;

    public SubmissionServiceImpl(
            SubmissionRepository submissionRepository,
            RestTemplate restTemplate) {

        this.submissionRepository =
                submissionRepository;

        this.restTemplate =
                restTemplate;
    }
    @Override
    public SubmissionResponse createSubmission(
            CreateSubmissionRequest request,
            String userEmail) {

        Submission submission = new Submission();

        submission.setProblemId(
                request.getProblemId());

        submission.setUserEmail(
                userEmail);

        submission.setLanguage(
                request.getLanguage());

        submission.setSourceCode(
                request.getSourceCode());

        submission.setStatus(
                SubmissionStatus.PENDING);

        submission.setSubmittedAt(
                LocalDateTime.now());

        Submission savedSubmission =
                submissionRepository.save(submission);

        JudgeRequest judgeRequest =
                new JudgeRequest();

        judgeRequest.setSubmissionId(
                savedSubmission.getId());

        JudgeResponse judgeResponse =
                restTemplate.postForObject(
                        "http://localhost:8084/api/judge/evaluate",
                        judgeRequest,
                        JudgeResponse.class
                );
        if (judgeResponse != null) {

            submission.setStatus(
                    SubmissionStatus.valueOf(
                            judgeResponse.getStatus()
                    )
            );

            submissionRepository.save(submission);
        }

        SubmissionResponse response =
                new SubmissionResponse();



        response.setId(
                savedSubmission.getId());

        response.setProblemId(
                savedSubmission.getProblemId());

        response.setUserEmail(
                savedSubmission.getUserEmail());

        response.setLanguage(
                savedSubmission.getLanguage());

        response.setStatus(
                savedSubmission.getStatus());

        response.setSubmittedAt(
                savedSubmission.getSubmittedAt());

        return response;
    }


    @Override
    public List<SubmissionResponse> getAllSubmissions() {

        List<Submission> submissions =
                submissionRepository.findAll();

        List<SubmissionResponse> responses =
                new ArrayList<>();

        for (Submission submission : submissions) {

            SubmissionResponse response =
                    new SubmissionResponse();

            response.setId(submission.getId());
            response.setProblemId(submission.getProblemId());
            response.setUserEmail(submission.getUserEmail());
            response.setLanguage(submission.getLanguage());
            response.setStatus(submission.getStatus());
            response.setSubmittedAt(submission.getSubmittedAt());

            responses.add(response);
        }

        return responses;
    }

    @Override
    public SubmissionResponse getSubmissionById(Long id) {

        Submission submission =
                submissionRepository.findById(id)
                        .orElseThrow(
                                () -> new SubmissionNotFoundException(
                                        "Submission not found")
                        );

        SubmissionResponse response =
                new SubmissionResponse();

        response.setId(submission.getId());
        response.setProblemId(submission.getProblemId());
        response.setUserEmail(submission.getUserEmail());
        response.setLanguage(submission.getLanguage());
        response.setStatus(submission.getStatus());
        response.setSubmittedAt(submission.getSubmittedAt());

        return response;
    }

    @Override
    public List<SubmissionResponse> getMySubmissions(
            String userEmail) {

        List<Submission> submissions =
                submissionRepository
                        .findByUserEmail(userEmail);

        List<SubmissionResponse> responses =
                new ArrayList<>();

        for (Submission submission : submissions) {

            SubmissionResponse response =
                    new SubmissionResponse();

            response.setId(submission.getId());
            response.setProblemId(submission.getProblemId());
            response.setUserEmail(submission.getUserEmail());
            response.setLanguage(submission.getLanguage());
            response.setStatus(submission.getStatus());
            response.setSubmittedAt(
                    submission.getSubmittedAt());

            responses.add(response);
        }

        return responses;
    }

}