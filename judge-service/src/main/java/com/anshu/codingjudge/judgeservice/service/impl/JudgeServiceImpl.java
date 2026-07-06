package com.anshu.codingjudge.judgeservice.service.impl;

import com.anshu.codingjudge.judgeservice.dto.EvaluateRequest;
import com.anshu.codingjudge.judgeservice.dto.EvaluateResponse;
import com.anshu.codingjudge.judgeservice.entity.JudgeResult;
import com.anshu.codingjudge.judgeservice.entity.JudgeStatus;
import com.anshu.codingjudge.judgeservice.repository.JudgeResultRepository;
import com.anshu.codingjudge.judgeservice.service.JudgeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JudgeServiceImpl implements JudgeService {

    private final JudgeResultRepository judgeResultRepository;

    public JudgeServiceImpl(
            JudgeResultRepository judgeResultRepository) {

        this.judgeResultRepository =
                judgeResultRepository;
    }

    @Override
    public EvaluateResponse evaluate(
            EvaluateRequest request) {

        JudgeResult result =
                new JudgeResult();

        result.setSubmissionId(
                request.getSubmissionId());

        String code =
                request.getSourceCode();

        System.out.println("Received Code = " + code);

        if (code == null || code.isBlank()) {

            result.setStatus(
                    JudgeStatus.COMPILATION_ERROR);

            result.setOutput(
                    "Source code is empty");

        }
        else if (code.contains("syntax_error")) {

            result.setStatus(
                    JudgeStatus.COMPILATION_ERROR);

            result.setOutput(
                    "Compilation failed");

        }
        else if (code.contains("runtime_error")) {

            result.setStatus(
                    JudgeStatus.RUNTIME_ERROR);

            result.setOutput(
                    "Runtime Exception occurred");

        }
        else if (code.contains("class Solution")) {

            result.setStatus(
                    JudgeStatus.ACCEPTED);

            result.setOutput(
                    "All test cases passed");

        }
        else {

            result.setStatus(
                    JudgeStatus.WRONG_ANSWER);

            result.setOutput(
                    "Expected class Solution");
        }

        result.setJudgedAt(
                LocalDateTime.now());

        JudgeResult savedResult =
                judgeResultRepository.save(result);

        EvaluateResponse response =
                new EvaluateResponse();

        response.setSubmissionId(
                savedResult.getSubmissionId());

        response.setStatus(
                savedResult.getStatus());

        return response;
    }
}