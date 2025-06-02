package com.demo.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
public class ApiTestBasics {

    @Test
    public void testGetPost() {
        // Setting base URI
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Sending  GET request
        Response response = RestAssured
                .given()
                .when()
                .get("/posts/1");


        // Printing response body
        System.out.println("Response Body:");
        System.out.println(response.getBody().asPrettyString());

        // Validating status code
        Assert.assertEquals(response.getStatusCode(), 200);

    }
}
