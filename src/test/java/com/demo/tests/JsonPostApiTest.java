package com.demo.tests;

import base.ApiBase;
import com.demo.payloads.Payloads;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExtentReportManager;

import java.io.IOException;
import java.util.List;

@Test
public class JsonPostApiTest {
    ApiBase apiBase = new ApiBase();

    @Test
    public void postWithJsonPayloads() throws IOException {

        RestAssured.baseURI = "https://reqres.in/"; // Default URI

        String fileDirectory = "src/test/resources/";
        String fileName = "postPayloads.json";

        // Load payloads from JSON file using ApiBase method
        List<Payloads> postPayloads = apiBase.loadPayloads(fileDirectory + fileName);


        for (Payloads postPayload : postPayloads) {
            var response = apiBase.post("api/users", postPayload);

            ExtentReportManager.getTest().info("POST Request Payload: " + postPayload.getName() + ", Job: " + postPayload.getJob());
            ExtentReportManager.getTest().info("Response Body:\n" + response.getBody().asPrettyString()); // Logs only to report
  // only print 'body'

            Assert.assertEquals(response.getStatusCode(), 201);
            Assert.assertEquals(response.jsonPath().getString("name"), postPayload.getName());
            Assert.assertEquals(response.jsonPath().getString("job"), postPayload.getJob());
        }

    }





}
