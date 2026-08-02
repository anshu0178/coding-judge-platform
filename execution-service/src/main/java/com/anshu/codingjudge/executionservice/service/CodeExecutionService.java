package com.anshu.codingjudge.executionservice.service;

public interface CodeExecutionService {

    String execute(
            String sourceCode,
            String input
    );
}
