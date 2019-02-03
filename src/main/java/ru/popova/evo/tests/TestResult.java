package ru.popova.evo.tests;

public class TestResult {
    String message;
    Boolean successfullyOfTest;

    public String getMessage() {
        return message;
    }

    public Boolean getSuccessfullyOfTest() {
        return successfullyOfTest;
    }

    public TestResult(String message, Boolean successfullyOfTest) {

        this.message = message;
        this.successfullyOfTest = successfullyOfTest;
    }
}
