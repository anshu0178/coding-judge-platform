package com.anshu.codingjudge.executionservice.dto;

public class ExecutionRequest {

    private String sourceCode;
    private String input;

    public ExecutionRequest() {
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}