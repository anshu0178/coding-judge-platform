package com.anshu.codingjudge.executionservice.service.impl;

import com.anshu.codingjudge.executionservice.service.JavaFileService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;

@Service
public class JavaFileServiceImpl implements JavaFileService {

    @Override
    public File createJavaFile(String sourceCode) throws Exception {

        File folder = new File("judge-temp");

        if (!folder.exists()) {
            folder.mkdir();
        }

        File javaFile = new File(folder, "Solution.java");

        FileWriter writer = new FileWriter(javaFile);

        writer.write(sourceCode);

        writer.close();

        System.out.println(
                "Java File Created : "
                        + javaFile.getAbsolutePath()
        );

        return javaFile;
    }
}