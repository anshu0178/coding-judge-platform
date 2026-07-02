package com.anshu.codingjudge.problemservice.controller;

import com.anshu.codingjudge.problemservice.dto.CreateProblemRequest;
import com.anshu.codingjudge.problemservice.entity.Problem;
import com.anshu.codingjudge.problemservice.service.ProblemService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/problems")
public class ProblemController {

    private final ProblemService problemService;

    public ProblemController(
            ProblemService problemService) {

        this.problemService = problemService;
    }

    @PostMapping
    public Problem createProblem(
            @Valid  @RequestBody CreateProblemRequest request) {

        return problemService.createProblem(request);
    }
}