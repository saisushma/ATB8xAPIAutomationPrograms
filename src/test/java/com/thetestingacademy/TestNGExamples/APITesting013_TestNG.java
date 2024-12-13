package com.thetestingacademy.TestNGExamples;

import org.testng.annotations.Test;

public class APITesting013_TestNG {

    @Test(priority = 1)
    public void t1(){
        System.out.println("t1");
    }
    @Test(priority =3)
    public void t2(){
        System.out.println("t2");
    }

    @Test(priority = 2)
    public void t3(){
        System.out.println("t3");
    }

    @Test(priority = 4)
    public void t4(){
        System.out.println("t4");
    }
}
