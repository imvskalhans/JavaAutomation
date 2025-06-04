package com.demo.tests;

import base.ApiBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExtentReportManager;

public class ApiTestBasics extends ApiBase {

    @Test
    public void testGetPost() {
        ExtentReportManager.startTest("testGetPost");

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = get("/posts/1");

        String body = response.getBody().asPrettyString();
        ExtentReportManager.getTest().info("Response:\n" + body);

        String title = response.jsonPath().getString("title");
        ExtentReportManager.getTest().info("Title: " + title);

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
