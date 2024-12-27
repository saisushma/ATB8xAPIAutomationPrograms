package com.thetestingacademy.Assertions.Practise;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.hamcrest.Matchers;
import static org.assertj.core.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class APITesting13_Assertions_real {

//post - create --> verify the response

    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;
    Response response;
    String token;
    Integer bookingID;


@Test
    public void test_post() {

    String payload_Post = " {\n" +
            "//            \"firstname\" : \"Sai\",\n" +
            "//                \"lastname\" : \"sushma\",\n" +
            "//                \"totalprice\" : 111,\n" +
            "//                \"depositpaid\" : true,\n" +
            "//                \"bookingdates\" : {\n" +
            "//            \"checkin\" : \"2024-01-01\",\n" +
            "//                    \"checkout\" : \"2024-02-01\"\n" +
            "//        },\n" +
            "//            \"additionalneeds\" : \"Breakfast\"\n" +
            "//        }";


    requestSpecification = RestAssured.given();
    requestSpecification.baseUri("https://restful-booker.herokuapp.com");
    requestSpecification.basePath("booking");
    requestSpecification.contentType(ContentType.JSON);
    requestSpecification.body(payload_Post).log().all();

    Response response = requestSpecification.when().post();

    Integer bookingId = response.then().extract().path("bookingid");

    //get validatable response to perform validation

    validatableResponse = response.then().log().all();
    validatableResponse.statusCode(200);

    //3 types of assertions

    //validatable response - 1  ==Hamcrest --> Rest Assured

    //rest Assured Default - Hamcrest
    //import org.hamcrest.Matchers;

    validatableResponse.body("booking.firstname",Matchers.equalTo("Sai"));
    validatableResponse.body("booking.lastname",Matchers.equalTo("sushma"));
    validatableResponse.body("booking.depositpaid",Matchers.equalTo(false));
    validatableResponse.body("bookingid",Matchers.notNullValue());


    //testNG assertions

    //Soft Asserts VS Hard Asserts
    //This means that if any assertions fails, the remaining statements in that test will not be executed

    bookingID = response.then().extract().path("bookingid");
    String firstName = response.then().extract().path("booking.firstname");
    String lastName = response.then().extract().path("booking.lastname");
    Assert.assertNotNull(bookingID);
    Assert.assertEquals(firstName,"Sai");
    Assert.assertEquals(lastName,"sushma");

    //AssertJ (3rd -lib and add it inthe pom.xml to assertions

    assertThat(bookingID).isNotNull().isPositive().isNotZero();
    assertThat(firstName).isEqualTo("Sai").isNotNull().isNotBlank().isNotEmpty().isAlphanumeric();

    String s = ""; //empty
    String s1 = " ";//space

}
}
