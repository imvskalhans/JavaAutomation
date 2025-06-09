package com.demo.tests;

import base.ApiBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExtentReportManager;

import static io.restassured.RestAssured.given;

public class GetApiTest extends ApiBase {

    @Test
    public void getTest01() {
        ExtentReportManager.startTest("testGetPost");

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = get("/posts/1");

        String body = response.getBody().asPrettyString();
        ExtentReportManager.getTest().info("Response:\n" + body);

        String title = response.jsonPath().getString("title");
        ExtentReportManager.getTest().info("Title: " + title);

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void getTest02(){
        ExtentReportManager.startTest("getTest02");

        //BDD style
        Response response =
                given()
                        .baseUri("https://jsonplaceholder.typicode.com")
                        .when()
                        .get("/posts/1")
                        .then()
                        .statusCode(200)
                        .log().body()
                        .extract().response();

        // Logging in extent report
        String body = response.getBody().asPrettyString();
        ExtentReportManager.getTest().info("Response:\n" + body);

        String title = response.jsonPath().getString("title");
        ExtentReportManager.getTest().info("Title: " + title);

        // Additional Assertion
        Assert.assertEquals(response.getStatusCode(), 200);

        // 1. GET Request
        Response getResponse =
                given()
                        .baseUri("https://jsonplaceholder.typicode.com")
                        .when()
                        .get("/posts")
                        .then()
                        .statusCode(200)
                        .log().body()
                        .extract().response();

        ExtentReportManager.getTest().info("GET Response:\n" + getResponse.asPrettyString());



    }
}
