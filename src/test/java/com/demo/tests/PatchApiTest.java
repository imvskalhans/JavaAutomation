package com.demo.tests;

import base.ApiBase;
import com.demo.payloads.Payloads;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExtentReportManager;

@Test
public class PatchApiTest extends ApiBase {

    @Test
    public void patchWithInlinePayload() {
        RestAssured.baseURI = "https://reqres.in/";

        // Inline payload for PATCH
        Payloads patchPayload = new Payloads();
        patchPayload.setName("Vishal Patched");
        patchPayload.setJob("DevOps Engineer");

        // Send PATCH request to update user ID 2
        Response response = patch("api/users/2", patchPayload);

        ExtentReportManager.getTest().info("PATCH Request Payload: " + patchPayload.getName() + ", Job: " + patchPayload.getJob());
        ExtentReportManager.getTest().info("Response Body:\n" + response.getBody().asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), patchPayload.getName());
        Assert.assertEquals(response.jsonPath().getString("job"), patchPayload.getJob());
    }
}
