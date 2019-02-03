package ru.popova.evo.json.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Answer implements JsonObject {

    private int code;
    private String description;

    @JsonCreator
    public Answer(@JsonProperty("code") int code, @JsonProperty("description") String description) {
        this.code = code;
        this.description = description;
    }

    @JsonGetter("code")
    public int getCode() {
        return code;
    }

    @JsonGetter("description")
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", description='" + description + '\'' +
                '}';
    }
}