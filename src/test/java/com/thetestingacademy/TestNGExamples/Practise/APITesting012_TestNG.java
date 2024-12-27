package com.thetestingacademy.TestNGExamples.Practise;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class APITesting012_TestNG {

   @BeforeTest
    public void getToken(){
        System.out.println("1");
    }
    @BeforeTest
    public void getBookingID(){
        System.out.println("2");
    }

    @Test
    public void testPut(){
        //need token and booking id to run before this method
        System.out.println("3");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("closingthe testcases");
    }
}
