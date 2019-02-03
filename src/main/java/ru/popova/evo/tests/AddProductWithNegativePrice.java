package ru.popova.evo.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.popova.evo.json.objects.Answer;
import ru.popova.evo.utils.requestSender;

public class AddProductWithNegativePrice extends Test {
    public AddProductWithNegativePrice(String host, ObjectMapper mapper) {
        this.host = host;
        this.mapper = mapper;
    }

    String name = "Добавление товара_price отрицательный";
    String host;
    ObjectMapper mapper;

    public TestResult execute() {
        try {
            Answer answer5 = mapper.readValue(requestSender.sendPostRequest(host + "/addProduct",
                    "{\"id\": 2,\"name\": \"Name2&\", \"price\": -101}"), Answer.class);
            boolean isSuccess = answer5.getCode() == 500 && (answer5.getDescription()).equals("Internal Server Error");
            if (isSuccess) {
                return new TestResult(String.format("Тест \"%s\" прошел успешно", name), isSuccess);
            } else return new TestResult(String.format("Тест \"%s\" упал", name), isSuccess);

        } catch (Exception e) {
            return new TestResult(String.format("Тест \"%s\" завершился с ошибкой", name) + e.getMessage(), false);
        }

    }
}
