package simulations;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

/**
 * This sample is based on our official tutorials:
 * <ul>
 *   <li><a href="https://gatling.io/docs/gatling/tutorials/quickstart">Gatling quickstart tutorial</a>
 *   <li><a href="https://gatling.io/docs/gatling/tutorials/advanced">Gatling advanced tutorial</a>
 * </ul>
 */
public class HelloSimulation extends Simulation {

    ChainBuilder hello = exec(http("Home").get("/hello"));

    HttpProtocolBuilder httpProtocol =
            http.baseUrl("https://127.0.0.1:8080/hello-service")
                    .basicAuth("user", "user")
                    .userAgentHeader(
                            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0"
                    );

    ScenarioBuilder users = scenario("Users").exec(hello);

    {
        setUp(
                users.injectOpen(rampUsers(100).during(30))
        ).protocols(httpProtocol);
    }
}
