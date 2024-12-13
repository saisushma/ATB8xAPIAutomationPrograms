package com.thetestingacademy.Integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;


public class APITesting_Practice_1 {

    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;
    Response response;
    String bookingID;
    String token;
    ValidatableResponse statusCode;

    public String getToken()
    {
        String tokenPayload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(tokenPayload);

        response = requestSpecification.when().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        token =  response.jsonPath().getString("token");

        System.out.println(token);

     return token;
    }

    @Test(priority = 1)
    public void create_booking(){

        String payload = "{\n" +
                "    \"firstname\" : \"Sai\",\n" +
                "    \"lastname\" : \"sushma\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2024-01-01\",\n" +
                "        \"checkout\" : \"2024-02-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload).log().all();

        response = requestSpecification.when().post();

        validatableResponse = response.then();
        validatableResponse.statusCode(200);

    }

    @Test(priority = 2)
    public void get_booking(){
        bookingID = response.jsonPath().getString("bookingid");
        System.out.println("Booking id is "+bookingID);
    }

    @Test(priority = 3)
    public void deleteBookingID(){

        token = getToken();
        System.out.println("booking id is: "+bookingID);
        System.out.println("Token is: "+token);
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingID);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);

        response= requestSpecification.when().delete();
        validatableResponse = response.then().log().all();
       statusCode = validatableResponse.statusCode(201);

        //Assert.assertEquals(statusCode, "201");
    }

    @Test(priority = 4)

    public void reRunDeletedBookingID()
    {
        deleteBookingID();
    }
}
