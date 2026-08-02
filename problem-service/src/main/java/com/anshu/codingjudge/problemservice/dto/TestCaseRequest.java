package com.anshu.codingjudge.problemservice.dto;

public class TestCaseRequest {

    private String input;
    private String expectedOutput;

    public TestCaseRequest() {
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(String expectedOutput) {
        this.expectedOutput = expectedOutput;
    }
}