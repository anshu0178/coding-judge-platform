package com.anshu.codingjudge.judgeservice.service;

public interface CodeExecutionService {

    String execute(
            String sourceCode,
            String input
    );

}