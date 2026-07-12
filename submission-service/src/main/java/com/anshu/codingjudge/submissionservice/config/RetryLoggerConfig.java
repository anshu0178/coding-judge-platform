package com.anshu.codingjudge.submissionservice.config;

import io.github.resilience4j.retry.RetryRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RetryLoggerConfig {

    private final RetryRegistry retryRegistry;

    public RetryLoggerConfig(
            RetryRegistry retryRegistry) {

        this.retryRegistry = retryRegistry;
    }

    @PostConstruct
    public void registerEvents() {

        retryRegistry.retry("judgeRetry")
                .getEventPublisher()
                .onRetry(event ->
                        System.out.println(
                                "Retry Attempt: "
                                        + event.getNumberOfRetryAttempts()
                        ));
    }
}