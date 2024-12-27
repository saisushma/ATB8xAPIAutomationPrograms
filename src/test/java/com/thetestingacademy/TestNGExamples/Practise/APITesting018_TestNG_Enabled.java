package com.thetestingacademy.TestNGExamples.Practise;

import org.testng.Assert;
import org.testng.annotations.Test;

public class APITesting018_TestNG_Enabled {

    @Test
    public void t1(){
        Assert.assertTrue(true);
    }
    @Test (enabled = false)
    public void t2(){

        System.out.println("2");
        Assert.assertTrue(true);
    }

    @Test(priority = 2)
    public void t3(){
        //need token and booking id to run before this method
        System.out.println("3");
    }
}
