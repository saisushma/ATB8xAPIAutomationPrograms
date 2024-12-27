package com.thetestingacademy.Integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;
import org.hamcrest.Matchers;

public class APITesting011_TC_Integration {

    //create a token
    //Create a booking
    //Perform a PUT req
    //Verify the PUT is success by GET Req
    //Delete the booking ID
    //Verify it doesnt exist GET req

    // whataever you will be sharing accross the Class, we create a variable
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    String token;
    String bookingId;

    public  String getToken()
    {
        String payload ="{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON).log().all();
        requestSpecification.body(payload);

        //when - response
         response = requestSpecification.when().post();

         //then
         validatableResponse = response.then();
         validatableResponse.statusCode(200);

         //extract the token
        token = response.jsonPath().getString("token");
        System.out.println(token);
        assertThat(token).isAlphanumeric().isNotEmpty().isNotNull().isNotBlank();

        return token;
    }
    public String getBookingId()
    {
        String payload_Post="{\n" +
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
        requestSpecification.contentType(ContentType.JSON).log().all();
        requestSpecification.body(payload_Post);
        //when
        response = requestSpecification.when().post();
        //then
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        bookingId = response.jsonPath().getString("bookingid");
        System.out.println(bookingId);
        assertThat(bookingId).isNotBlank().isNotNull().isNotEmpty();

        return bookingId;

        //https://restful-booker.herokuapp.com/booking
    }

    @Test(priority = 1)
    public void test_update_request_put(){
        token = getToken();
        System.out.println("Token is : "+token);
        bookingId = getBookingId();
        System.out.println("Booking ID is :" +bookingId);

        String payload_put="{\n" +
                "    \"firstname\" : \"Sai\",\n" +
                "    \"lastname\" : \"Sharma\",\n" +
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
        requestSpecification.basePath("/booking/"+bookingId);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);
        requestSpecification.body(payload_put).log().all();

        response = requestSpecification.when().put();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        validatableResponse.body("booking.firstname", Matchers.equalTo("Sai"));
        validatableResponse.body("bookingid",Matchers.notNullValue());

        Assert.assertNotNull(bookingId);
        Assert.assertEquals("booking.firstname", "Sai");
    }

    @Test(priority = 2)
    public void test_update_request_get(){
        System.out.println(bookingId);

        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingId);
        response = requestSpecification.when().log().all().get();
       validatableResponse = response.then().log().all();
       validatableResponse .statusCode(200);

       //TestNG
        String lName = response.jsonPath().getString("lastname");
        Assert.assertEquals(lName,"Sharma");

        //rest assured Matchers
        validatableResponse.body("booking.lastname",Matchers.equalTo("Sharma"));
        validatableResponse.body("booking.lastname",Matchers.notNullValue());

        //assertJ
        assertThat(bookingId).isNotEmpty().isNotNull().isAlphanumeric();


    }

    @Test(priority= 3)
    public void test_delete_booking(){
        System.out.println(bookingId);
        System.out.println(token);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingId);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);

        response = requestSpecification.when().delete();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201); // #TODO #1 - delete bug

        //using rest assured matching
        validatableResponse.body("bookingid",Matchers.empty());

        //testNG assert
        Assert.assertNull(bookingId);

        //AssertJ

        assertThat(bookingId).isNull();


    }

    @Test(priority = 4)
    public void test_aftr_delete_req_get(){
        System.out.println(bookingId);
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/" +bookingId);
        response = requestSpecification.when().log().all().get();
        requestSpecification.then().log().all().statusCode(404);

    }

    //


}
