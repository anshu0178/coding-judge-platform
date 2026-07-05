package com.anshu.codingjudge.judgeservice.service;

import com.anshu.codingjudge.judgeservice.dto.EvaluateRequest;
import com.anshu.codingjudge.judgeservice.dto.EvaluateResponse;

public interface JudgeService {

    EvaluateResponse evaluate(
            EvaluateRequest request);
}