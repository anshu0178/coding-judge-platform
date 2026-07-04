package com.anshu.codingjudge.submissionservice.repository;

import com.anshu.codingjudge.submissionservice.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository
        extends JpaRepository<Submission, Long> {
    List<Submission> findByUserEmail(String userEmail);
}