package com.thetestingacademy.Assertions.Practise;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class APITesting13_Assertions {

    //testNG Assertions
    //verify your expected result and actual result
//
//    @Test
//    public void test_hardAssertExample(){
//        System.out.println("start of the program");
//        Assert.assertTrue(false); //here testcase fails, so next set of code will not run
//        System.out.println("end of the program");
//    }

    @Test
    public void test_softAssertExample(){
        SoftAssert softAssert = new SoftAssert();
        System.out.println("start of the program");
        Assert.assertTrue(false); //here testcase fails, but next set of code will run
        System.out.println("this line will get executed");
        softAssert.assertAll();
    }

}
