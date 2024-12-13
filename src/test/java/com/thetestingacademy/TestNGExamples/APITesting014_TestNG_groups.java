package com.thetestingacademy.TestNGExamples;

import org.testng.Assert;
import org.testng.annotations.Test;

public class APITesting014_TestNG_groups {

    @Test(groups={"qa","sanity"})
    public void sanityRun(){
        System.out.println("Sanity");
        System.out.println("QA");
        Assert.assertTrue(true);
    }
    @Test(groups={"qa","smoke","prepod"})
    public void smokeRun(){
        System.out.println("smoke");
        System.out.println("QA");
        System.out.println("prepod");
        Assert.assertTrue(true);
    }

    @Test(groups = {"reg","prod"})
    public void sanityRun1(){
        System.out.println("prod");
        System.out.println("reg");
        Assert.assertTrue(true);
    }

    @Test(priority = 4)
    public void t4(){
        System.out.println("t4");
    }
}
