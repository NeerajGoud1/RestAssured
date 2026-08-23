package com.restassured.demo;


import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TraditionalRestAssuredTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void testPostRequestTraditional() {

        System.out.println("=== TRADITIONAL APPROACH ===\n");

        JSONObject requestBody = new JSONObject(); // 1. Create JSON Object
        requestBody.put("title", "Test Post");
        requestBody.put("body", "This is a test post body");
        requestBody.put("userId", 1);

        System.out.println("Request Body: " + requestBody.toString());

        RequestSpecification request = given(); // 2. Create Request Specification
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());

        Response response = request.post("/posts"); // 3. Send POST Request

        int statusCode = response.getStatusCode(); // 4. Get Status Code
        System.out.println("Status Code: " + statusCode);

        System.out.println("Complete Response: " + response.asString()); // 5. Print Response

        String responseBody = response.getBody().asString(); // 6. Print Response Body
        System.out.println("Response Body: " + responseBody);

        String statusLine = response.getStatusLine(); // 7. Print Response Status Line
        System.out.println("Response Status Line: " + statusLine);

        String contentType = response.getContentType(); // 8. Print Content Type
        System.out.println("Response Content Type: " + contentType);

        long responseTime = response.getTime(); // 9. Display Response Time
        System.out.println("Response Time: " + responseTime + " ms");

        org.testng.Assert.assertEquals(statusCode, 201, "Status code should be 201");
        org.testng.Assert.assertTrue(responseBody.contains("Test Post"),
                "Response should contain the title");
        org.testng.Assert.assertNotNull(responseBody,
                "Response body should not be null");
    }
}