package com.anshu.codingjudge.judgeservice.controller;

import com.anshu.codingjudge.judgeservice.dto.EvaluateRequest;
import com.anshu.codingjudge.judgeservice.dto.EvaluateResponse;
import com.anshu.codingjudge.judgeservice.service.JudgeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/judge")
public class JudgeController {

    private final JudgeService judgeService;

    public JudgeController(
            JudgeService judgeService) {

        this.judgeService = judgeService;
    }

    @PostMapping("/evaluate")
    public EvaluateResponse evaluate(
            @RequestBody EvaluateRequest request) {

        return judgeService.evaluate(request);
    }
}