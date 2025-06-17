package base;

import com.demo.payloads.Payloads;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import utils.ExtentReportManager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;


public class ApiBase {

    @BeforeMethod(alwaysRun = true)
    public void startExtent(Method method) {
        ExtentReportManager.startTest(method.getName(), "Executing: " + method.getName());
    }

    public List<Payloads> loadPayloads(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), new TypeReference<>() {});
    }
    // Generic GET request
    public Response get(String endpoint) {
        return RestAssured
                .given()
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    // Generic POST request with JSON body
    public Response post(String endpoint, Object body) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("x-api-key", "reqres-free-v1")
                .log().all()
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
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    // PUT
    public Response put(String endpoint, Object body) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("x-api-key", "reqres-free-v1")
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    // PATCH
    public Response patch(String endpoint, Object body) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("x-api-key", "reqres-free-v1")
                .body(body)
                .when()
                .patch(endpoint)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }


    // DELETE
    public Response delete(String endpoint) {
        return RestAssured
                .given()
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }
}
