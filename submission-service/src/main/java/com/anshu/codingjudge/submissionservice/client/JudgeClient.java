package com.anshu.codingjudge.submissionservice.client;

import com.anshu.codingjudge.submissionservice.dto.judge.JudgeRequest;
import com.anshu.codingjudge.submissionservice.dto.judge.JudgeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "JUDGE-SERVICE")
public interface JudgeClient {

    @PostMapping("/api/judge/evaluate")
    JudgeResponse evaluate(
            @RequestBody JudgeRequest request
    );
}