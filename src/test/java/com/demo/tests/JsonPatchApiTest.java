package com.demo.tests;

import base.ApiBase;
import com.demo.payloads.Payloads;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExtentReportManager;

import java.io.IOException;
import java.util.List;

@Test
public class JsonPatchApiTest extends ApiBase {

    @Test
    public void patchWithJsonPayloads() throws IOException {
        RestAssured.baseURI = "https://reqres.in/";

        String filePath = "src/test/resources/patchPayloads.json";
        String fileDirectory = "src/test/resources/";
        String fileName = "patchPayloads.json";

        //call inherited loadPayloads() method
        List<Payloads> patchPayloads = loadPayloads(fileDirectory + fileName);


        for (Payloads payload : patchPayloads) {
            Response response = patch("api/users/2", payload);

            ExtentReportManager.getTest().info("PATCH Payload: " + payload.getName() + ", Job: " + payload.getJob());
            ExtentReportManager.getTest().info("PATCH Response:\n" + response.getBody().asPrettyString());

            Assert.assertEquals(response.getStatusCode(), 200);
            Assert.assertEquals(response.jsonPath().getString("name"), payload.getName());
            Assert.assertEquals(response.jsonPath().getString("job"), payload.getJob());
        }
    }
}
