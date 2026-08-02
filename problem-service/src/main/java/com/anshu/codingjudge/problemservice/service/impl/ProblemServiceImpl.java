package com.anshu.codingjudge.problemservice.service.impl;

import com.anshu.codingjudge.problemservice.dto.CreateProblemRequest;
import com.anshu.codingjudge.problemservice.dto.TestCaseResponse;
import com.anshu.codingjudge.problemservice.entity.Problem;
import com.anshu.codingjudge.problemservice.entity.TestCase;
import com.anshu.codingjudge.problemservice.exception.ProblemNotFoundException;
import com.anshu.codingjudge.problemservice.repository.ProblemRepository;
import com.anshu.codingjudge.problemservice.repository.TestCaseRepository;
import com.anshu.codingjudge.problemservice.service.ProblemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProblemServiceImpl
        implements ProblemService {

    private final ProblemRepository problemRepository;
    private final TestCaseRepository testCaseRepository;

    public ProblemServiceImpl(
            ProblemRepository problemRepository,
            TestCaseRepository testCaseRepository) {

        this.problemRepository = problemRepository;
        this.testCaseRepository = testCaseRepository;
    }

    @Override
    public Problem createProblem(CreateProblemRequest request) {

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

        Problem savedProblem = problemRepository.save(problem);

        for (var tc : request.getTestCases()) {

            TestCase testCase = new TestCase();

            testCase.setInput(tc.getInput());
            testCase.setExpectedOutput(tc.getExpectedOutput());
            testCase.setHidden(true);
            testCase.setProblem(savedProblem);

            testCaseRepository.save(testCase);
        }

        return savedProblem;
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

    @Override
    public List<TestCaseResponse> getTestCases(Long problemId) {

        List<TestCase> testCases =
                testCaseRepository.findByProblemId(problemId);

        List<TestCaseResponse> responses =
                new ArrayList<>();

        for (TestCase testCase : testCases) {

            if (testCase.isHidden()) {

                TestCaseResponse response =
                        new TestCaseResponse();

                response.setInput(testCase.getInput());

                response.setExpectedOutput(
                        testCase.getExpectedOutput());

                responses.add(response);
            }
        }

        return responses;
    }


}
