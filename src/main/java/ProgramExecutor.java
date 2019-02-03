import com.fasterxml.jackson.databind.ObjectMapper;
import ru.popova.evo.tests.*;
import ru.popova.evo.utils.Property;

import java.io.File;
import java.util.ArrayList;

public class ProgramExecutor {

    public static void main(String args[]) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Property property = mapper.readValue(new File("src/main/resources/property.json"), Property.class);
        String host = property.getHost();

        ArrayList<Test> tests = new ArrayList<>();
        tests.add(new AddProdudtWithAllParams(host, mapper));
        tests.add(new AddProductWithoutId(host, mapper));
        tests.add(new AddProductWithoutName(host, mapper));
        tests.add(new AddProductWithoutPrice(host, mapper));
        tests.add(new AddProductWithNegativePrice(host, mapper));
        tests.add(new AddProductRepeatId(host, mapper));
        tests.add(new GetProductIdNotEntered(host, mapper));
        tests.add(new GetProductIdNotFound(host, mapper));
        tests.add(new GetProductIdFound(host, mapper));


        TestExecutor executor = new TestExecutor(tests, property.getPathTestResult(), "AllTests");

        executor.executeAllTests(tests);
    }
}