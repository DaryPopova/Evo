package ru.popova.evo.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Property {

    @JsonCreator
    public Property(@JsonProperty("host") String host,
                    @JsonProperty("path_test_result") String pathTestResult
                    ) {
        this.host = host;
        this.pathTestResult = pathTestResult;
    }

    private String host;
    private String pathTestResult;

    public String getHost() {
        return host;
    }

    public String getPathTestResult() {
        return pathTestResult;
    }
}
