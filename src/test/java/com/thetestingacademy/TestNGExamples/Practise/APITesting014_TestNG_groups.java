package com.thetestingacademy.TestNGExamples.Practise;

import org.testng.Assert;
import org.testng.annotations.Test;

public class APITesting014_TestNG_groups {

   @Test(groups ={ "sanity", "qa","prepod" } )
    public void sanityRun(){

       System.out.println("Sanity");
       System.out.println("QA");
       Assert.assertTrue(true);
    }
    @Test (groups = {"qa","prepod","reg"})
    public void RegRun()
    {
        System.out.println("Reg");
        Assert.assertTrue(false);
    }

    @Test(groups = {"dev","stage"})
    public void smokeRun(){
        //need token and booking id to run before this method
        System.out.println("smoke");
        Assert.assertTrue(false);
    }
    @Test(groups = {"qa","prepod","reg"})
    public void RegRun2() {
        //need token and booking id to run before this method
        System.out.println("smoke");
    }
    @Test(groups ={ "sanity", "qa","prepod" })
    public void sanityRun1(){
        //need token and booking id to run before this method
        System.out.println("smoke");
        Assert.assertTrue(false);
    }
}
