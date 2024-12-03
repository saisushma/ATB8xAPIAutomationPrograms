package com.thetestingacademy.RestAssuredBasics.GET;

public class APITest004_BuilderDesignPattern {

    //change return type of each method type as class type
    //"this" always point to the current/calling object
    //
    public APITest004_BuilderDesignPattern step1()
    {
        System.out.println("step1 is started");
        System.out.println("step 1 is done");

        return this;
    }
    public APITest004_BuilderDesignPattern step2()
    {
        System.out.println("step2 is started");
        System.out.println("step2 is done");

        return this;
    }
    public APITest004_BuilderDesignPattern step3(String name)
    {
        System.out.println("step3 is started");
        System.out.println("step3 is done -> "+name);

        return this;
    }
    public static void main(String[] args) {
        APITest004_BuilderDesignPattern bp = new APITest004_BuilderDesignPattern();
//        np.step1();
//        np.step2();
//        np.step3("Pramod");

        bp.step1().step2().step3("Pramod");
    }
}
