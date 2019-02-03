package ru.popova.evo.tests;


import com.fasterxml.jackson.databind.ObjectMapper;
import ru.popova.evo.json.objects.Answer;
import ru.popova.evo.utils.requestSender;

public class AddProductWithoutName extends Test {
    public AddProductWithoutName(String host, ObjectMapper mapper) {
        this.host = host;
        this.mapper = mapper;
    }

    String name = "Добавление товара_name отсутствует";
    String host;
    ObjectMapper mapper;

    public TestResult execute() {
        try {
            Answer answer3 = mapper.readValue(requestSender.sendPostRequest(host + "/addProduct",
                    "{\"id\": 1, \"price\": 101}"), Answer.class);
            boolean isSuccess = answer3.getCode() == 400 && (answer3.getDescription()).equals("Bad Request");

            if (isSuccess) {
                return new TestResult(String.format("Тест \"%s\" прошел успешно", name), isSuccess);
            } else return new TestResult(String.format("Тест \"%s\" упал", name), isSuccess);

        } catch (Exception e) {
            return new TestResult(String.format("Тест \"%s\" завершился с ошибкой", name) + e.getMessage(), false);
        }
    }

}
