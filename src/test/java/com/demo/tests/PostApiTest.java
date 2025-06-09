package com.demo.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ExtentReportManager;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostApiTest {
    @Test
    public void postTest01(){
        ExtentReportManager.startTest("postTest01");

        // POST #1
        String post01 = """
                {
                    "title": "New Post",
                    "body": "This is a new post",
                    "userId": 101
                }
                """;

        Response postResponse =
                given()
                        .baseUri("https://jsonplaceholder.typicode.com")
                        .contentType(ContentType.JSON)
                        .body(post01)
                        .when()
                        .post("/posts")
                        .then()
                        .statusCode(201)
                        .log().body()
                        .body("title", equalTo("New Post"))
                        .body("userId", equalTo(101))
                        .extract().response();

        ExtentReportManager.getTest().info("POST Response:\n" + postResponse.asPrettyString());

        // POST #2
        String post02 = """
                {
                    "title": "New Post 02",
                    "body": "2nd post by Vishal",
                    "userId": 102
                }
                """;

        Response postResponse02 =
                given()
                        .baseUri("https://jsonplaceholder.typicode.com")
                        .contentType(ContentType.JSON)
                        .body(post02)
                        .when()
                        .post("/posts")
                        .then()
                        .statusCode(201)
                        .log().body()
                        .body("title", equalTo("New Post 02"))
                        .body("userId", equalTo(102))
                        .extract().response();

        ExtentReportManager.getTest().info("Second POST Response:\n" + postResponse02.asPrettyString());

        // POST #3
        String post03 = """
                {
                    "title": "New Post 03",
                    "body": "3rd post testing",
                    "userId": 103
                }
                """;

        Response response03 =
                given()
                        .baseUri("https://jsonplaceholder.typicode.com")
                        .contentType(ContentType.JSON)
                        .body(post03)
                        .when()
                        .post("/posts")
                        .then()
                        .statusCode(201)
                        .log().body()
                        .body("title", equalTo("New Post 03"))
                        .body("userId", equalTo(103))
                        .extract().response();

        ExtentReportManager.getTest().info("Third POST Response:\n" + response03.asPrettyString());
    }
}
