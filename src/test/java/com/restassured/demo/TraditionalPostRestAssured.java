package com.restassured.demo;


import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TraditionalPostRestAssured {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    private JSONObject testUser;
    private int createdUserId;

    @BeforeClass
    public void setup() {

        RestAssured.baseURI = BASE_URL;

        testUser = new JSONObject();
        testUser.put("name", "Test User");
        testUser.put("email", "sai@gmail.com");
        testUser.put("username", "sai");
    }

    @Test(priority = 1)
    public void testPostRequestComplete() {

        System.out.println("\n\n========== POST REQUEST - TRADITIONAL ==========");

        JSONObject user = new JSONObject(testUser.toString());
        user.put("id", 1);

        System.out.println("Request Body: " + user.toString(2));

        Response response = given()
                .header("Content-Type", "application/json")
                .body(user.toString())
                .post("/users");

        printResponseDetails(response, "POST");

        org.testng.Assert.assertEquals(
                response.getStatusCode(),
                201,
                "Status code should be 201"
        );

        JSONObject responseJson =
                new JSONObject(response.getBody().asString());

        if (responseJson.has("id")) {

            createdUserId = responseJson.getInt("id");

            System.out.println("Created User ID: " + createdUserId);
        }
    }

    private void printResponseDetails(Response response, String requestType) {

        System.out.println("\n========== " + requestType + " RESPONSE ==========");

        System.out.println("Status Code: " + response.getStatusCode());

        System.out.println("Complete Response:");
        System.out.println(response.asPrettyString());

        System.out.println("Response Body:");
        System.out.println(response.getBody().asPrettyString());

        System.out.println("Status Line: " + response.getStatusLine());

        System.out.println("Content Type: " + response.getContentType());

        System.out.println("Response Time: " + response.getTime() + " ms");
    }
}
