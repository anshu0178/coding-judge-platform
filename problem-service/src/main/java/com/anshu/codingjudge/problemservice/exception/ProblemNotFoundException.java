package com.anshu.codingjudge.problemservice.exception;

public class ProblemNotFoundException
        extends RuntimeException {

    public ProblemNotFoundException(
            String message) {

        super(message);
    }
}