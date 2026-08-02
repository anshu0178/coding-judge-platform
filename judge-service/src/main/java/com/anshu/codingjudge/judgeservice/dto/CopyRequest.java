package com.anshu.codingjudge.judgeservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CopyRequest {

    private String containerName;
    private String sourceCode;
}