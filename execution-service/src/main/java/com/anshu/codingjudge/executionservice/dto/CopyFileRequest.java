package com.anshu.codingjudge.executionservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CopyFileRequest {

    private String containerName;

    private String sourceCode;
}