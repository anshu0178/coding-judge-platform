package com.anshu.codingjudge.judgeservice.client;

import com.anshu.codingjudge.judgeservice.dto.TestCaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PROBLEM-SERVICE")
public interface ProblemClient {

    @GetMapping("/api/problems/{id}/testcases")
    List<TestCaseResponse> getTestCases(
            @PathVariable("id") Long problemId);
}