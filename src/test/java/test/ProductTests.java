package test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.*;
import pojo.product.Payload;
import pojo.product.ProductApi;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductTests extends BaseTest{

    private static long product_ID;
    private static long variant_ID;
    ObjectMapper mapper = new ObjectMapper();
    Payload newPayload = new ProductApi().generateRandomProductPayload();


    @Order(1)
    @DisplayName("Creating a product with a default variant ")
    @Test
    public void createProduct() throws JsonProcessingException {

        System.out.println("Create product newPayload: \n" + mapper.writeValueAsString(newPayload) );

        Response response = given()
                                    .spec(reqSpec)
                                    .body(newPayload).
                            when()
                                     .post("admin/api/2021-07/products.json").
                            then()
                                    .assertThat()
                                    .statusCode(201)
                                    //.log().body()
                                    .contentType(ContentType.JSON)
                                    .extract()
                                    .response();

        JsonPath jsonPath = response.jsonPath();
        product_ID = jsonPath.getLong("product.id");  //storing product_id in a static = class level variable
                 System.out.println("Created a product with id  = " + product_ID);

        variant_ID = jsonPath.getLong("product.variants[0].id");
                System.out.println("variantId = " + variant_ID);
    }


    @Order(2)
    @DisplayName("Verify access to the storefront homepage")
    @Test
    public void GetStorefrontHomepage() {

         Response response =  given()
                                    .spec(reqSpec)
                                    .header("Accept", "application/json").
                              when()
                                     .get();

         assertThat(response.statusCode(), is (200));
         assertThat(response.getBody().asPrettyString(), containsStringIgnoringCase("Universe of Birds"));

         String jsonString = response.asString();
         Assertions.assertTrue(jsonString.contains("All The Birds"));
         System.out.println("Storefront Home Page has been accessed!");

    }


    @Order(3)
    @DisplayName("Retrieve a single product")
    @Test()
    public void retrieveSingleProduct() {

      JsonPath jsonPath = given()
                                    .spec(reqSpec)
                                    .header("published_status","published").
                          when()
                                    .get("admin/api/2021-07/products/" + String.valueOf(product_ID) + ".json").
                          then()
                                   // .log().body()
                                    .assertThat()
                                    .statusCode(200)
                                    .body("product.status",is("active"))
                                    .body("product.published_scope", is ("web"))
                                    .extract()
                                    .response().jsonPath();

      String expectedTitle = newPayload.getProduct().getTitle();
      String actualTitle = jsonPath.getString("product.title");
      Assertions.assertTrue(expectedTitle.equals(actualTitle),"Expected and actual titles do not match!");
             System.out.println("Verified the product is displayed on the home page!");

    }


    @Order(4)
    @DisplayName("Verify product is added to cart ")
    @Test
    public void addToCart()  {

        String payload = new ProductApi().getAddToCartPayload(String.valueOf(variant_ID));
/*
        try {
            System.out.println("Add to cart payload: \n" + mapper.writeValueAsString(payload) );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

      */  given()
                .spec(reqSpec)
                .body(payload).
        when()
                .post("cart/add.js").
        then()
                .assertThat()
                .log().body()
                .statusCode(200);

        System.out.println("Added the product to cart with variant id = " + variant_ID);

    }


    @Order(5)
    @DisplayName("DELETE a published product")
    @Test()
    public void deleteProduct() {

        given()
                .spec(reqSpec).
        when()
                .delete("admin/api/2021-07/products/"+String.valueOf(product_ID)+".json").
        then()
                .log().body()
                .assertThat()
                .statusCode(200);

        System.out.println("Deleted the product");

    }








}
