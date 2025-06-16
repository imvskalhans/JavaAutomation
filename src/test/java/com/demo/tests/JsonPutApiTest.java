package com.demo.tests;

import base.ApiBase;
import com.demo.payloads.PostPayload;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExtentReportManager;

import java.io.IOException;
import java.util.List;

@Test
public class JsonPutApiTest extends ApiBase {

    @Test
    public void putWithJsonPayloads() throws IOException {

        RestAssured.baseURI = "https://reqres.in/";

        String fileDirectory = "src/test/resources/";
        String fileName = "putPayloads.json";

        // ✅ Corrected: call inherited loadPostPayloads() method
        List<PostPayload> putPayloads = loadPostPayloads(fileDirectory + fileName);

        for (PostPayload payload : putPayloads) {
            // PUT on a static ID (for example purposes)
            Response response = put("api/users/2", payload);

            // Log in report
            ExtentReportManager.getTest().info("PUT Payload: " + payload.getName() + ", Job: " + payload.getJob());
            ExtentReportManager.getTest().info("PUT Response:\n" + response.getBody().asPrettyString());

            // Assertions
            Assert.assertEquals(response.getStatusCode(), 200);
            Assert.assertEquals(response.jsonPath().getString("name"), payload.getName());
            Assert.assertEquals(response.jsonPath().getString("job"), payload.getJob());
        }
    }
}
