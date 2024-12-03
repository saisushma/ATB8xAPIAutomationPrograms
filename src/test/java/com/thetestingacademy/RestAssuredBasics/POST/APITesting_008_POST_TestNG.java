package com.thetestingacademy.RestAssuredBasics.POST;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class APITesting_008_POST_TestNG {

    //https://restful-booker.herokuapp.com/auth

//            -H 'Content-Type: application/json'
//            -d '{
//            "username" : "admin",
//            "password" : "password123"
    @Description("Verify the post Req - BDD style")
    @Test
    public void test_post_BddStyle(){

        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        RestAssured
                .given()
                    .baseUri("https://restful-booker.herokuapp.com")
                    .basePath("/auth")
                    .contentType(ContentType.JSON)
                    .log().all().body(payload)
                .when()
                    .log().all().post()
                .then()
                    .log().all().statusCode(200);

}
}
