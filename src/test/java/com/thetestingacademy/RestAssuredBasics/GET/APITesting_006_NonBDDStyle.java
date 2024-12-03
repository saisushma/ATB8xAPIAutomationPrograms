package com.thetestingacademy.RestAssuredBasics.GET;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting_006_NonBDDStyle {

    static RequestSpecification r = RestAssured.given();

    @Severity(SeverityLevel.CRITICAL)
    @Description("TC 1 - non BDD style get - positive testcase")
    @Test
    public void test_NonBDDStyle_GET_POS() {
        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/560037");
        r.when().log().all().get();
        r.then().log().all().statusCode(200);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("TC 1 - non BDD style get - negative testcase")
    @Test
    public void test_NonBDDStyle_GET_NEG() {
        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/-1");
        r.when().log().all().get();
        r.then().log().all().statusCode(200);
    }

}