package com.thetestingacademy.TestNGExamples.Practise;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class APITesting017_TestNG_parameter {

    @Parameters("browser")
    @Test
    void demo(String value){
        System.out.println("browser is "+value);
        //open the browser and select dadaad..
        if(value.equalsIgnoreCase("chrome")){
            System.out.println("start my chrome");
        }
        if(value.equalsIgnoreCase("firefox")){
            System.out.println("start my firefox");
        }
    }
}
