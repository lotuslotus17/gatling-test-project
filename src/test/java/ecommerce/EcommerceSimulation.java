package ecommerce;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

public class EcommerceSimulation extends Simulation {

    //Setting up base parameters
    HttpProtocolBuilder httpProtocol =
        http.baseUrl("https://automationexercise.com")
            .acceptHeader("application/json;q=0.9,*/*;q=0.8")
            .acceptLanguageHeader("en-US,en;q=0.5")
            .acceptEncodingHeader("gzip, deflate")
            .userAgentHeader(
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0"
            );

    //Test description using open model
    {
        setUp(
            Scenario.createUserAndSearchForProduct
                    .injectOpen(
                            //user ramp up
                            rampUsers(5).during(10),
                            //user hold
                            constantUsersPerSec(5).during(10))
        ).protocols(httpProtocol);
    }
}
