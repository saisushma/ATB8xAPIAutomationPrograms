package com.thetestingacademy.PayloadManagement.difficultWay;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting_018_Pojo_Payload_diff {

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

        //pojo -> json

        Booking_diff booking = new Booking_diff();
        booking.setFirstname("sai");
        booking.setLastname("Dutta");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        BookingDates_diff bookingDatesDiff = new BookingDates_diff();
        bookingDatesDiff.setCheckin("2024-02-01");
        bookingDatesDiff.setCheckOut("2024-02-02");
        booking.setBookingDates(bookingDatesDiff);
        booking.setAdditionalNeeds("breakfast");

        System.out.println(booking);


        //json -> hashmap



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
        requestSpecification.body(booking).log().all();

        Response response = requestSpecification.when().post();

        Integer bookingId = response.then().extract().path("bookingid");

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

    }
}
