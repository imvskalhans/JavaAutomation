package base;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiBase {
    public Response get(String endpoint) {
        return RestAssured
                .given()
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }
}
