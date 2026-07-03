package com.anshu.codingjudge.problemservice.service.impl;

import com.anshu.codingjudge.problemservice.dto.CreateProblemRequest;
import com.anshu.codingjudge.problemservice.entity.Problem;
import com.anshu.codingjudge.problemservice.exception.ProblemNotFoundException;
import com.anshu.codingjudge.problemservice.repository.ProblemRepository;
import com.anshu.codingjudge.problemservice.service.ProblemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemServiceImpl
        implements ProblemService {

    private final ProblemRepository problemRepository;

    public ProblemServiceImpl(
            ProblemRepository problemRepository) {

        this.problemRepository = problemRepository;
    }

    @Override
    public Problem createProblem(
            CreateProblemRequest request) {

        Problem problem = new Problem();

        problem.setTitle(request.getTitle());
        problem.setDescription(request.getDescription());
        problem.setDifficulty(request.getDifficulty());
        problem.setInputFormat(request.getInputFormat());
        problem.setOutputFormat(request.getOutputFormat());
        problem.setSampleInput(request.getSampleInput());
        problem.setSampleOutput(request.getSampleOutput());
        problem.setConstraints(request.getConstraints());
        problem.setCreatedBy(request.getCreatedBy());

        return problemRepository.save(problem);
    }
    @Override
    public List<Problem> getAllProblems() {
        return problemRepository.findAll();
    }
    @Override
    public Problem getProblemById(Long id) {

        return problemRepository.findById(id)
                .orElseThrow(() ->
                        new ProblemNotFoundException(
                                "Problem not found with id " + id));
    }
    @Override
    public Problem updateProblem(
            Long id,
            CreateProblemRequest request) {

        Problem problem = problemRepository
                .findById(id)
                .orElseThrow(() ->
                        new ProblemNotFoundException(
                                "Problem not found with id " + id));

        problem.setTitle(request.getTitle());
        problem.setDescription(request.getDescription());
        problem.setDifficulty(request.getDifficulty());
        problem.setInputFormat(request.getInputFormat());
        problem.setOutputFormat(request.getOutputFormat());
        problem.setSampleInput(request.getSampleInput());
        problem.setSampleOutput(request.getSampleOutput());
        problem.setConstraints(request.getConstraints());

        return problemRepository.save(problem);
    }
    @Override
    public void deleteProblem(Long id) {

        Problem problem = problemRepository
                .findById(id)
                .orElseThrow(() ->
                        new ProblemNotFoundException(
                                "Problem not found with id " + id));

        problemRepository.delete(problem);
    }
}
