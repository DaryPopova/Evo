package ru.popova.evo.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.popova.evo.json.objects.Answer;
import ru.popova.evo.utils.requestSender;

public class GetProductIdNotEntered extends Test {
    public GetProductIdNotEntered(String host, ObjectMapper mapper) {
        this.host = host;
        this.mapper = mapper;
    }

    String name = "Получение товара_id отсутствует";
    String host;
    ObjectMapper mapper;

    public TestResult execute() {
        try {
            Answer answer7 = mapper.readValue(requestSender.sendGetRequest(host + "/getProduct?"), Answer.class);
            boolean isSuccess = answer7.getCode() == 400 && (answer7.getDescription()).equals("Bad Request");
            if (isSuccess) {
                return new TestResult(String.format("Тест \"%s\" прошел успешно", name), isSuccess);
            } else return new TestResult(String.format("Тест \"%s\" упал", name), isSuccess);

        } catch (Exception e) {
            return new TestResult(String.format("Тест \"%s\" завершился с ошибкой", name) + e.getMessage(), false);
        }

    }
}
