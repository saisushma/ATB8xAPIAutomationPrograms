package com.thetestingacademy.sampleCheck;

import  io.restassured.RestAssured;

public class APITest002 {
    public static void main(String[] args) {

        //Gherkins syntax
        //        given() - url, headers, body or paylaod
        //        when() - http methods - get, post, patch, put, delete
        //        then() - verify the response - er == ar

        //full URL -  https://restful-booker.herokuapp.com/booking/1
       //base URI - https://restful-booker.herokuapp.com
        //base path - /booking/1

        RestAssured
                .given()
                    .baseUri("https://restful-booker.herokuapp.com")
                    .basePath("/booking/1")
                .when()
                    .get()
                .then().log().all()
                .statusCode(200);
    }
}
