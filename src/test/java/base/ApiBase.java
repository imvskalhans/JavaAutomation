package base;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

import java.util.Map;

public class ApiBase {

    static {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com"; // Default URI
    }

    // Generic GET request
    public Response get(String endpoint) {
        return RestAssured
                .given()
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    // Generic POST request with JSON body
    public Response post(String endpoint, Object body) {
        return RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    // POST with headers and body
    public Response post(String endpoint, Object body, Map<String, String> headers) {
        return RestAssured
                .given()
                .log().all()
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    // PUT
    public Response put(String endpoint, Object body) {
        return RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    // DELETE
    public Response delete(String endpoint) {
        return RestAssured
                .given()
                .log().all()
                .when()
                .delete(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }
}
