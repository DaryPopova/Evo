package ru.popova.evo.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.popova.evo.json.objects.Answer;
import ru.popova.evo.utils.requestSender;


public class AddProductWithoutId extends Test {
    public AddProductWithoutId(String host, ObjectMapper mapper) {
        this.host = host;
        this.mapper = mapper;
    }

    String name = "Добавление товара_id отсутствует";
    String host;
    ObjectMapper mapper;

    public TestResult execute() {
        try {
            Answer answer2 = mapper.readValue(requestSender.sendPostRequest(host + "/addProduct",
                    "{\"name\": \"Name1\", \"price\": 101}"), Answer.class);
            boolean isSuccess = answer2.getCode() == 400 && (answer2.getDescription()).equals("Bad Request");
            if (isSuccess) {
                return new TestResult(String.format("Тест \"%s\" прошел успешно", name), isSuccess);
            } else return new TestResult(String.format("Тест \"%s\" упал", name), isSuccess);

        } catch (Exception e) {
            return new TestResult(String.format("Тест \"%s\" завершился с ошибкой", name) + e.getMessage(), false);
        }

    }
}