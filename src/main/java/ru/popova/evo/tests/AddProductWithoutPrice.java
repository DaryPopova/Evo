package ru.popova.evo.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.popova.evo.json.objects.Answer;
import ru.popova.evo.utils.requestSender;

public class AddProductWithoutPrice extends Test {
    public AddProductWithoutPrice(String host, ObjectMapper mapper) {
        this.host = host;
        this.mapper = mapper;
    }

    String name = "Добавление товара_price отсутствует";
    String host;
    ObjectMapper mapper;

    public TestResult execute() {
        try {
            Answer answer4 = mapper.readValue(requestSender.sendPostRequest(host + "/addProduct",
                    "{\"id\": 1,\"name\": \"Name1\"}"), Answer.class);
            boolean isSuccess = answer4.getCode() == 400 && (answer4.getDescription()).equals("Bad Request");
            if (isSuccess) {
                return new TestResult(String.format("Тест \"%s\" прошел успешно", name), isSuccess);
            } else return new TestResult(String.format("Тест \"%s\" упал", name), isSuccess);

        } catch (Exception e) {
            return new TestResult(String.format("Тест \"%s\" завершился с ошибкой", name) + e.getMessage(), false);
        }

    }
}
