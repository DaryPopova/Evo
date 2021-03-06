package ru.popova.evo.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.popova.evo.json.objects.Answer;
import ru.popova.evo.utils.requestSender;

public class GetProductIdNotFound extends Test {
    public GetProductIdNotFound(String host, ObjectMapper mapper) {
        this.host = host;
        this.mapper = mapper;
    }

    String name = "Получение товара_товар не найден";
    String host;
    ObjectMapper mapper;

    public TestResult execute() {
        try {
            Answer answer8 = mapper.readValue(requestSender.sendGetRequest(host + "/getProduct?id=4"), Answer.class);
            boolean isSuccess = answer8.getCode() == 404 && (answer8.getDescription()).equals("Not Found");
            if (isSuccess) {
                return new TestResult(String.format("Тест \"%s\" прошел успешно", name), isSuccess);
            } else return new TestResult(String.format("Тест \"%s\" упал", name), isSuccess);

        } catch (Exception e) {
            return new TestResult(String.format("Тест \"%s\" завершился с ошибкой", name) + e.getMessage(), false);
        }

    }
}
