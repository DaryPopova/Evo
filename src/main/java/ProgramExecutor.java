import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;

public class ProgramExecutor {

    public static void main(String args[]) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        //Добавление товара_все параметры присутствуют и валидны
        Answer answer1 = mapper.readValue(Utils.sendPostRequest("http://localhost:8080/addProduct",
                "{\"id\": 1,\"name\": \"Name1&\", \"price\": 101}"), Answer.class);
        System.out.println(answer1);
        System.out.println(answer1.getCode() == 200 && (answer1.getDescription()).equals("success"));

        // Добавление товара_id отсутствует
        Answer answer2 = mapper.readValue(Utils.sendPostRequest("http://localhost:8080/addProduct",
                "{\"name\": \"Name1\", \"price\": 101}"), Answer.class);
        System.out.println(answer2);
        System.out.println(answer2.getCode() == 400 && (answer2.getDescription()).equals("Bad Request"));

        // Добавление товара_name отсутствует
        Answer answer3 = mapper.readValue(Utils.sendPostRequest("http://localhost:8080/addProduct",
                "{\"id\": 1, \"price\": 101}"), Answer.class);
        System.out.println(answer3);
        System.out.println(answer3.getCode() == 400 && (answer3.getDescription()).equals("Bad Request"));

        // Добавление товара_price отсутствует
        Answer answer4 = mapper.readValue(Utils.sendPostRequest("http://localhost:8080/addProduct",
                "{\"id\": 1,\"name\": \"Name1\"}"), Answer.class);
        System.out.println(answer4);
        System.out.println(answer4.getCode() == 400 && (answer4.getDescription()).equals("Bad Request"));

        //Добавление товара_price отрицательный
        Answer answer5 = mapper.readValue(Utils.sendPostRequest("http://localhost:8080/addProduct",
                "{\"id\": 2,\"name\": \"Name2&\", \"price\": -101}"), Answer.class);
        System.out.println(answer5);
        System.out.println(answer5.getCode() == 500 && (answer5.getDescription()).equals("Internal Server Error"));


        //Добавление товара_id уже присутствует
        Utils.sendPostRequest("http://localhost:8080/addProduct",
                "{\"id\": 3,\"name\": \"Name3&\", \"price\": 102}");
        Answer answer6 = mapper.readValue(Utils.sendPostRequest("http://localhost:8080/addProduct",
                "{\"id\": 3,\"name\": \"Name4&\", \"price\": 103}"), Answer.class);
        System.out.println(answer6);
        System.out.println(answer6.getCode() == 500 && (answer6.getDescription()).equals("Internal Server Error"));

        //Получение товара_id отсутствует
        Answer answer7 = mapper.readValue(Utils.sendGetRequest("http://localhost:8080/getProduct?"), Answer.class);
        System.out.println(answer7);
        System.out.println(answer7.getCode() == 400 && (answer7.getDescription()).equals("Bad Request"));

        //Получение товара_товар не найден
        Answer answer8 = mapper.readValue(Utils.sendGetRequest("http://localhost:8080/getProduct?id=4"), Answer.class);
        System.out.println(answer8);
        System.out.println(answer8.getCode() == 404 && (answer8.getDescription()).equals("Not Found"));

        //Получение товара_товар найден
        Utils.sendPostRequest("http://localhost:8080/addProduct",
                "{\"id\": 1,\"name\": \"Name1&\", \"price\": 101}");
        Product answer9 = mapper.readValue(Utils.sendGetRequest("http://localhost:8080/getProduct?id=1"), Product.class);
        System.out.println(answer9);
        System.out.println(answer9.getId() == 1 && answer9.getName().equals("Name1&") && answer9.getPrice() == 101.0);
    }
}