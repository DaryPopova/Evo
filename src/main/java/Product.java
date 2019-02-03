import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    private Integer id;
     private String name;
     private double price;

    @JsonCreator
    public Product(@JsonProperty("id") Integer id,
                   @JsonProperty("name") String name,
                   @JsonProperty("price") double price) throws Exception {
        this.id = id;
        this.name = name;
        if (price > 0) {
            this.price = price;
        } else throw new Exception("price must > 0");
    }

    @JsonGetter("id")
    public Integer getId() {
        return id;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    @JsonGetter("price")
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}