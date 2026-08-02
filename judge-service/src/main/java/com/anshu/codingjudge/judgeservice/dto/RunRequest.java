package com.anshu.codingjudge.judgeservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RunRequest {

    private String containerName;

    private String input;

}