import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;

public class ProgramExecutor {
    public static void main(String args[]) throws Exception {


        /*
        System.out.println("Hello");
        ObjectMapper objectMapper = new ObjectMapper();

        String productJson =
                "{\"id\": 12, \"name\": \"Mercedes\", \"price\": 335235}";

        Product product1 = objectMapper.readValue(productJson, Product.class);

        System.out.println(product1.getId());
        System.out.println(product1.getName());
        System.out.println(product1.getPrice());

        Product product2 = new Product(14, "ferry", 123456);
        String json = objectMapper.writeValueAsString(product2);
        System.out.println(json);
           */

        ObjectMapper mapper = new ObjectMapper(); // just need one
        //Answer answer1 = mapper.readValue(new URL("http://localhost:8080/addProduct?id=3&name=Name1&price=101"), Answer.class);
        //System.out.println(answer1);
        //Answer answer2 = mapper.readValue(new URL("http://localhost:8080/getProduct?id=3"), Answer.class);
        //System.out.println(answer2);
        Answer answer3 = mapper.readValue(new URL("http://localhost:8080/getProduct?id=4"), Answer.class);
        System.out.println(answer3);
    }
}