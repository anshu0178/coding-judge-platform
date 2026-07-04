package com.anshu.codingjudge.submissionservice.exception;

public class SubmissionNotFoundException
        extends RuntimeException {

    public SubmissionNotFoundException(
            String message) {

        super(message);
    }
}