package com.anshu.codingjudge.judgeservice.service.impl;

import com.anshu.codingjudge.judgeservice.service.CodeExecutionService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;

@Service
public class CodeExecutionServiceImpl implements CodeExecutionService {

    @Override
    public String execute(String sourceCode, String input) {

        try {

            File folder = new File("judge-temp");

            if (!folder.exists()) {
                folder.mkdir();
            }

            File javaFile = new File(folder, "Solution.java");

            FileWriter writer = new FileWriter(javaFile);

            writer.write(sourceCode);

            writer.close();

            System.out.println("Java File Created : "
                    + javaFile.getAbsolutePath());

            ProcessBuilder builder =
                    new ProcessBuilder(
                            "javac",
                            javaFile.getAbsolutePath()
                    );

            Process process = builder.start();

            int exitCode = process.waitFor();

            if (exitCode == 0) {

                System.out.println("Compilation Successful");

                ProcessBuilder runBuilder =
                        new ProcessBuilder(
                                "java",
                                "-cp",
                                folder.getAbsolutePath(),
                                "Solution"
                        );

                Process runProcess = runBuilder.start();
// Send input to the program
                runProcess.getOutputStream().write(input.getBytes());
                runProcess.getOutputStream().flush();
                runProcess.getOutputStream().close();

                runProcess.waitFor();

// Read output
                String output = new String(
                        runProcess.getInputStream().readAllBytes()
                );

                System.out.println("Program Output : " + output);

                return output.trim();

            } else {

                System.out.println("Compilation Failed");

                return "COMPILATION_ERROR";
            }

        } catch (Exception e) {

            e.printStackTrace();

            return "ERROR";
        }
    }
}