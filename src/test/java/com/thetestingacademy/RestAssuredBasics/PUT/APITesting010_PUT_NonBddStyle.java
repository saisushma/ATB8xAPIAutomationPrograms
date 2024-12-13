package com.thetestingacademy.RestAssuredBasics.PUT;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting010_PUT_NonBddStyle {

    RequestSpecification rs = RestAssured.given();
    // need bookid
    //token  - 6d8c132d2febcb8
    // payload
//        {
//            "bookingid": 2511,
//                "booking": {
//            "firstname": "Jim",
//                    "lastname": "Brown",
//                    "totalprice": 111,
//                    "depositpaid": true,
//                    "bookingdates": {
//                "checkin": "2018-01-01",
//                        "checkout": "2019-01-01"
//            },
//            "additionalneeds": "Breakfast"
//        }
//        }

    @Description("Verify the PUT request for the restful Booker APIs")
    @Test
    public void test_put_nonbdd(){

        String token = "6d8c132d2febcb8";
        String bookingid = "7059";

        String payloadPUT = "{\n" +
                "    \"firstname\" : \"Sai\",\n" +
                "    \"lastname\" : \"Sushma\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        //get
        rs.baseUri("https://restful-booker.herokuapp.com");
        rs.basePath("/booking/"+bookingid);
        rs.contentType(ContentType.JSON);
        rs.cookie("token",token);
        rs.auth().preemptive().basic("admin","password123");
        rs.body(payloadPUT).log().all();
      //when
       Response rp = rs.when().put();
       //then
        ValidatableResponse vr = rp.then().log().all();
        vr.statusCode(200);


    }
}
