package com.demo.tests;

import base.ApiBase;
import com.demo.payloads.PostPayload;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExtentReportManager;

@Test
public class PutApiTest extends ApiBase {

    @Test
    public void putWithInlinePayloads() {
        RestAssured.baseURI = "https://reqres.in/";

        PostPayload payload = new PostPayload();
        payload.setName("Vishal Updated");
        payload.setJob("Software Development Engineer");

        // Use inherited method
        Response response = put("api/users/2", payload);



        // Now the @BeforeMethod from ApiBase will execute
        ExtentReportManager.getTest().info("PUT Request Payload: " + payload.getName() + ", Job: " + payload.getJob());
        ExtentReportManager.getTest().info("Response Body:\n" + response.getBody().asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), payload.getName());
        Assert.assertEquals(response.jsonPath().getString("job"), payload.getJob());
    }
}





