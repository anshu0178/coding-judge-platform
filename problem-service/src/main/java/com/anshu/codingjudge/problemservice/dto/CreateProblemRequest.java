package com.anshu.codingjudge.problemservice.dto;

import com.anshu.codingjudge.problemservice.entity.Difficulty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProblemRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private Difficulty difficulty;
    private String inputFormat;
    private String outputFormat;
    private String sampleInput;
    private String sampleOutput;
    private String constraints;
    private String createdBy;

    public CreateProblemRequest() {
    }

    // Generate getters and setters
}