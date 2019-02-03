package ru.popova.evo.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.popova.evo.json.objects.Product;
import ru.popova.evo.utils.requestSender;

public class GetProductIdFound extends Test {
    public GetProductIdFound(String host, ObjectMapper mapper) {
        this.host = host;
        this.mapper = mapper;
    }

    String name = "Получение товара_товар найден";
    String host;
    ObjectMapper mapper;

    public TestResult execute() {
        try {
            requestSender.sendPostRequest("http://localhost:8080/addProduct",
                    "{\"id\": 1,\"name\": \"Name1&\", \"price\": 101}");
            Product answer9 = mapper.readValue(requestSender.sendGetRequest(host + "/getProduct?id=1"), Product.class);
            boolean isSuccess = answer9.getId() == 1 && answer9.getName().equals("Name1&") && answer9.getPrice() == 101.0;
            if (isSuccess) {
                return new TestResult(String.format("Тест \"%s\" прошел успешно", name), isSuccess);
            } else return new TestResult(String.format("Тест \"%s\" упал", name), isSuccess);

        } catch (Exception e) {
            return new TestResult(String.format("Тест \"%s\" завершился с ошибкой", name) + e.getMessage(), false);
        }

    }
}
