package ru.popova.evo.tests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class TestExecutor {

    private List<Test> tests;
    private String pathToReport;
    private String nameOfreport;

    public TestExecutor(List<Test> tests, String pathToReport, String nameOfreport) {
        this.tests = tests;
        this.pathToReport = pathToReport;
        this.nameOfreport = nameOfreport;
    }

    public void executeAllTests(List<Test> tests) throws IOException {
        long successCount = 0;
        long unsuccessCount = 0;

        List<TestResult> testResultList = tests.stream().map(Test::execute).collect(Collectors.toList());
        List<TestResult> successTests = testResultList.stream().filter(TestResult::getSuccessfullyOfTest).collect(Collectors.toList());
        List<TestResult> unsuccessTests = testResultList.stream().filter(testResult -> !testResult.getSuccessfullyOfTest()).collect(Collectors.toList());

        successCount = successTests.size();
        unsuccessCount = unsuccessTests.size();

        String filePath = pathToReport + "/" + nameOfreport + "_" + (int)(System.currentTimeMillis() / 1000);
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(String.format("Всего запущено тестов %s \n", tests.size()));
        writer.write(String.format("Тестов проведено %s \n", successCount + unsuccessCount));
        writer.write(String.format("Тестов прошло неуспешно: %s \n", unsuccessCount));
        writer.write(String.format("Тестов прошло успешно: %s \n", successCount));
        writer.write("\n");
        writer.write(String.format("Список неуспешных тестов:\n", unsuccessTests));
        for (TestResult test: unsuccessTests) {
            writer.write(test.getMessage() + "\n");
        }
        writer.write("\n");
        writer.write(String.format("Список успешных тестов:\n", successTests));
        for (TestResult test: successTests) {
            writer.write(test.getMessage() + "\n");
        }
        writer.close();
        }
    }

