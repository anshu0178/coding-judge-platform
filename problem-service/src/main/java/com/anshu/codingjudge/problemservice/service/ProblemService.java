package com.anshu.codingjudge.problemservice.service;

import com.anshu.codingjudge.problemservice.dto.CreateProblemRequest;
import com.anshu.codingjudge.problemservice.entity.Problem;

import java.util.List;

public interface ProblemService {

    Problem createProblem(
            CreateProblemRequest request);

    List<Problem> getAllProblems();

    Problem getProblemById(Long id);

    Problem updateProblem(
            Long id,
            CreateProblemRequest request);
    void deleteProblem(Long id);
}