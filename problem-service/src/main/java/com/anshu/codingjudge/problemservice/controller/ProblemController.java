package com.anshu.codingjudge.problemservice.controller;

import com.anshu.codingjudge.problemservice.dto.CreateProblemRequest;
import com.anshu.codingjudge.problemservice.entity.Problem;
import com.anshu.codingjudge.problemservice.service.ProblemService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<Problem> getAllProblems() {

        return problemService.getAllProblems();
    }

    @GetMapping("/{id}")
    public Problem getProblemById(
            @PathVariable Long id) {

        return problemService.getProblemById(id);
    }

    @PutMapping("/{id}")
    public Problem updateProblem(
            @PathVariable Long id,
            @Valid @RequestBody CreateProblemRequest request) {

        return problemService.updateProblem(
                id,
                request);
    }

    @DeleteMapping("/{id}")
    public String deleteProblem(
            @PathVariable Long id) {

        problemService.deleteProblem(id);

        return "Problem deleted successfully";
    }

}