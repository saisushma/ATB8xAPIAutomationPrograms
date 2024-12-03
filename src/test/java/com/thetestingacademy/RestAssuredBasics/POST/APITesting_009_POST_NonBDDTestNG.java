package com.thetestingacademy.RestAssuredBasics.POST;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting_009_POST_NonBDDTestNG {

    //https://restful-booker.herokuapp.com/auth

//            -H 'Content-Type: application/json'
//            -d '{
//            "username" : "admin",
//            "password" : "password123"
    RequestSpecification r = RestAssured.given();
    @Description("Verify the post Req - Non BDD style")
    @Test
    public void test_post_BddStyle(){

        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";


               r.baseUri("https://restful-booker.herokuapp.com");
               r.basePath("/auth");
               r.contentType(ContentType.JSON).log().all();
               r.body(payload);
               r.when().post();
               r.then().log().all().statusCode(200);

}
}
