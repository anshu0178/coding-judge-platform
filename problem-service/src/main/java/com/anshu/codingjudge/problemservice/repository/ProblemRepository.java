package com.anshu.codingjudge.problemservice.repository;

import com.anshu.codingjudge.problemservice.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository
        extends JpaRepository<Problem, Long> {
}