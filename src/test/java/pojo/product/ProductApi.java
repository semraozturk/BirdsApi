package pojo.product;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class ProductApi {



    public Payload generateRandomProductPayload(){
        Faker faker = new Faker();

        Payload payload = new Payload();
        Product product = new Product();
        String[] productTypes = {"Cardinal","Parrot","Nightingale","Falcon","Eagle"};

        product.setTitle("Test Product");
        product.setBody_html("<strong> Amazing product!</strong>");
        product.setVendor(faker.company().name());
        product.setProduct_type(productTypes[faker.number().numberBetween(0,4)]);
        product.setTags(getTags());

        payload.setProduct(product);
        return payload;
    }

    private List<String> getTags(){
        Faker faker = new Faker();
        List<String> tags = new ArrayList<>();
        String[] tagList = {"A", "B", "C", "D","E"};
        int iteration = faker.random().nextInt(2,4);
        for (int i = 0; i < iteration; i++) {
            tags.add(tagList[i]);
        }
        return tags;
    }


    public String getAddToCartPayload(String id){
        return "{\n" +
                "\"items\": [\n" +
                "  {\n" +
                "    \"quantity\": 1,\n" +
                "    \"id\": " + id + "\n" +
                "    \n" +
                "  }\n" +
                "]\n" +
                "}";
    }



}
