package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import pojo.product.Payload;
import pojo.product.ProductApi;
import utility.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.reset;
import static org.hamcrest.Matchers.is;

public class BaseTest {


    protected static RequestSpecification reqSpec;

    @BeforeAll
    public static void setUp(){

        baseURI = ConfigurationReader.getProperty("app.baseUri");
        reqSpec = given()
                        .header("Content-Type","application/json")
                        .header("X-Shopify-Access-Token", ConfigurationReader.getProperty("app.access.token"));
    }


    @AfterAll
    public static void cleanUp(){
        reset();
    }








}
