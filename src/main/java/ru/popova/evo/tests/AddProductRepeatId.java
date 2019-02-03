package ru.popova.evo.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.popova.evo.json.objects.Answer;
import ru.popova.evo.utils.requestSender;

public class AddProductRepeatId extends Test {
    public AddProductRepeatId(String host, ObjectMapper mapper) {
        this.host = host;
        this.mapper = mapper;
    }

    String name = "Добавление товара_id уже присутствует";
    String host;
    ObjectMapper mapper;

    public TestResult execute() {
        try {
            requestSender.sendPostRequest(host + "/addProduct",
                    "{\"id\": 3,\"name\": \"Name3&\", \"price\": 102}");
            Answer answer6 = mapper.readValue(requestSender.sendPostRequest(host + "/addProduct",
                    "{\"id\": 3,\"name\": \"Name4&\", \"price\": 103}"), Answer.class);
            boolean isSuccess = answer6.getCode() == 500 && (answer6.getDescription()).equals("Internal Server Error");
            if (isSuccess) {
                return new TestResult(String.format("Тест \"%s\" прошел успешно", name), isSuccess);
            } else return new TestResult(String.format("Тест \"%s\" упал", name), isSuccess);

        } catch (Exception e) {
            return new TestResult(String.format("Тест \"%s\" завершился с ошибкой", name) + e.getMessage(), false);
        }

    }
}
