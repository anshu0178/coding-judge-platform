package com.anshu.codingjudge.executionservice.service;

import java.io.File;

public interface JavaFileService {

    File createJavaFile(String sourceCode) throws Exception;

}