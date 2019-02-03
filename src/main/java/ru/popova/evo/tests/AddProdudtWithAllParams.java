package ru.popova.evo.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.popova.evo.json.objects.Answer;
import ru.popova.evo.utils.requestSender;

public class AddProdudtWithAllParams extends Test {
    public AddProdudtWithAllParams(String host, ObjectMapper mapper) {
        this.host = host;
        this.mapper = mapper;
    }

    String name = "Добавление товара_все параметры присутствуют и валидны";
    String host;
    ObjectMapper mapper;

    public TestResult execute() {
        try {
            Answer answer1 = mapper.readValue(requestSender.sendPostRequest(host + "/addProduct",
                    "{\"id\": 1,\"name\": \"Name1&\", \"price\": 101}"), Answer.class);
            boolean isSuccess = answer1.getCode() == 200 && (answer1.getDescription()).equals("success");
            if (isSuccess) {
                return new TestResult(String.format("Тест \"%s\" прошел успешно", name), isSuccess);
            } else return new TestResult(String.format("Тест \"%s\" упал", name), isSuccess);

        } catch (Exception e) {
            return new TestResult(String.format("Тест \"%s\" завершился с ошибкой", name) + e.getMessage(), false);
        }

    }
}
