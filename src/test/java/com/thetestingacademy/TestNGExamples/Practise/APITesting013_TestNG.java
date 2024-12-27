package com.thetestingacademy.TestNGExamples.Practise;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class APITesting013_TestNG {

   @Test(priority = 1)
    public void t1(){
        System.out.println("1");
    }
    @Test (priority = 3)
    public void t2(){
        System.out.println("2");
    }

    @Test(priority = 2)
    public void t3(){
        //need token and booking id to run before this method
        System.out.println("3");
    }
}
