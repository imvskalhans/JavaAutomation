package com.demo.tests;

import base.ApiBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExtentReportManager;

import java.util.HashMap;
import java.util.Map;

@Test
public class DeleteApiTest extends ApiBase {

    @Test
    public void deleteSingleUser() {
        RestAssured.baseURI = "https://reqres.in/";

        // Headers
        Map<String, String> headers = new HashMap<>();
        headers.put("x-api-key", "reqres-free-v1");

        // Deleting user with ID 2 using headers
        Response response = delete("api/users/2", headers);

        ExtentReportManager.getTest().info("DELETE Request Sent for user ID: 2 with headers");
        ExtentReportManager.getTest().info("Response Status Code: " + response.getStatusCode());

        // Reqres returns 204 for successful deletion
        Assert.assertEquals(response.getStatusCode(), 204);
    }
}
