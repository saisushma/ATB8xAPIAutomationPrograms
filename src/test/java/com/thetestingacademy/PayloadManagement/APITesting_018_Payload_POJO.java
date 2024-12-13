package com.thetestingacademy.PayloadManagement;
import  com.thetestingacademy.PayloadManagement.gson.Booking;
import  com.thetestingacademy.PayloadManagement.gson.Bookingdates;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;


public class APITesting_018_Payload_POJO {

    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;

    @Test
    public void testPostReq(){

        //pojo class -> JSON
        Booking booking = new Booking();
        booking.setFirstname("sai");
        booking.setLastname("sushma");
        booking.setTotalprice(212);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-01-01");
        bookingdates.setCheckout("2024-02-02");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(booking).log().all();

        Response response = requestSpecification.when().post();

        Integer bookingId = response.then().extract().path("bookingid");

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        System.out.println("booking id: " +bookingId);


    }


}
