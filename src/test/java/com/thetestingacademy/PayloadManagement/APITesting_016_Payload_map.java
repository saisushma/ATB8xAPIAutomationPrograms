package com.thetestingacademy.PayloadManagement;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.ValidatableResponse;

import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class APITesting_016_Payload_map {

    @Test
    public void testPostReq(){
        RequestSpecification requestSpecification = RestAssured.given();
        ValidatableResponse validatableResponse;

        //Map ->JSON can be converted by - GSON/JacksonAPI library

//        {
//            "firstname" : "Sai",
//                "lastname" : "sushma",
//                "totalprice" : 111,
//                "depositpaid" : true,
//                "bookingdates" : {
//            "checkin" : "2024-01-01",
//                    "checkout" : "2024-02-01"
//        },
//            "additionalneeds" : "Breakfast"
//        }
        //json -> hashmap



        Map<String,Object> jsonBodyUsingMap = new LinkedHashMap<>();
        jsonBodyUsingMap.put("firstname", "Sai");
        jsonBodyUsingMap.put("lastname","sushma");
        jsonBodyUsingMap.put("totalPrice",123);
        jsonBodyUsingMap.put("depositPaid", true);

        Map<String,Object> bookingDateMap = new LinkedHashMap<>();
        bookingDateMap.put("checkin", "2024-01-01");
        bookingDateMap.put("checkout", "2024-02-01");

        jsonBodyUsingMap.put("bookingdates", bookingDateMap);

        jsonBodyUsingMap.put("additionalneeds","Breakfast");

        System.out.println(jsonBodyUsingMap);
        //{firstname=Sai,
        // lastname=sushma,
        // totalPrice=123,
        // depositPaid=true,
        // bookingdates=
        //  {checkin=2024-01-01, checkout=2024-02-01},
        //  additionalneeds=Breakfast}

        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonBodyUsingMap).log().all();

        Response response = requestSpecification.when().post();

        Integer bookingId = response.then().extract().path("bookingid");

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

    }
}
