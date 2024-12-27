package com.thetestingacademy.TestNGExamples.Practise;

import org.testng.Assert;
import org.testng.annotations.Test;

public class APITesting20_TestNG_invocationCOunt {

    @Test(invocationCount = 5)
    public void t1(){
        Assert.assertTrue(true);
    }

}
