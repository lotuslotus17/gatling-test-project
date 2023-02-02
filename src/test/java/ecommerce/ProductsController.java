package ecommerce;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.Session;
import io.gatling.javaapi.http.*;

import java.util.Random;

public class ProductsController {


    //Get request to get all the products
    public static ChainBuilder getAllProducts =
            exec(http("GetAllProducts")
                    .get("/api/productsList")
                    .header("content-type", "application/json")
                    //extracting category parameter
                    .check(jsonPath("$..products..category.category").saveAs("category"))
                    .check(status().is(200)));

    //Post request to search products by category
    public static ChainBuilder searchProductByCategory =
            exec(http("SearchProductByCategory")
                    .post("/api/searchProduct")
                    .header("content-type", "application/json")
                    //using body from json template with category parameter extracted earlier
                    .body(ElFileBody("body/search_products.json")).asJson()
                    .check(status().is(200)));

    //Post request to create user
    public static ChainBuilder createUser =
            exec(session -> {
                Session newSession = session
                        .set("firstName", "ExampleName")
                        .set("lastName", "ExampleLastName");
                        return newSession;
            })
            .exec(http("CreateUser")
                    .post("/api/createAccount")
                    .header("content-type", "application/json")
                    //using body from json template with parameters from feeder
                    .body(ElFileBody("body/create_user.json")).asJson()
                    .check(status().is(200)));
}
