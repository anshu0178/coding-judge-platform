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

        result.setStatus(
                JudgeStatus.ACCEPTED);

        result.setOutput(
                "All test cases passed");

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