package com.thetestingacademy.TestNGExamples.Practise;

import org.testng.Assert;
import org.testng.annotations.Test;

public class APITesting015_TestNG_DependsOnMethod {

    @Test
    public void serverStartedOk(){
        System.out.println("i will run first");
        Assert.assertTrue(true);
    }
    @Test(dependsOnMethods = "serverStartedOk")
    public void method1(){
        System.out.println("method1");
        Assert.assertTrue(true);
    }
    @Test(dependsOnMethods = "serverStartedOk")
    public void method2(){
        System.out.println("method2");
        Assert.assertTrue(true);
    }
}
