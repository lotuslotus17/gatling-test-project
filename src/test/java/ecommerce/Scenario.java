package ecommerce;

import static io.gatling.javaapi.core.CoreDsl.*;

import io.gatling.javaapi.core.*;

public class Scenario {

    //Simple scenario user gets created than gets all products info and searching for specific products by category
    public static ScenarioBuilder createUserAndSearchForProduct =
            scenario("created_user_searching_for_product_by_category")
                    //data for user creation request
                    .feed(csv("feeders/user_creation.csv").random())
                    .exec(ProductsController.createUser)
                    .pause(1)
                    .exec(ProductsController.getAllProducts)
                    .pause(1)
                    .exec(ProductsController.searchProductByCategory);
}
