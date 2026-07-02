package com.anshu.codingjudge.problemservice.service;

import com.anshu.codingjudge.problemservice.dto.CreateProblemRequest;
import com.anshu.codingjudge.problemservice.entity.Problem;

public interface ProblemService {

    Problem createProblem(
            CreateProblemRequest request);
}