package com.restassured.demo;


import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TraditionalRestAssuredTest2 {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void testGetCommentsTraditional() {

        System.out.println("=== TRADITIONAL APPROACH ===\n");

        // 1. Create Request Specification
        RequestSpecification request = given();

        // 2. Send GET Request
        Response response = request.get("/posts/1/comments");

        // 3. Get Status Code
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);

        // 4. Print Complete Response
        System.out.println("Complete Response: " + response.asString());

        // 5. Print Response Body
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

        // 6. Print Response Status Line
        String statusLine = response.getStatusLine();
        System.out.println("Response Status Line: " + statusLine);

        // 7. Print Content Type
        String contentType = response.getContentType();
        System.out.println("Response Content Type: " + contentType);

        // 8. Display Response Time
        long responseTime = response.getTime();
        System.out.println("Response Time: " + responseTime + " ms");

        // 9. Assertions
        org.testng.Assert.assertEquals(statusCode, 200,
                "Status code should be 200");

        org.testng.Assert.assertNotNull(responseBody,
                "Response body should not be null");

        org.testng.Assert.assertTrue(responseBody.contains("postId"),
                "Response should contain postId");
    }
}
