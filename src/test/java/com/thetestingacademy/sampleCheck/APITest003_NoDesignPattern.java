package com.thetestingacademy.sampleCheck;

public class APITest003_NoDesignPattern {

    public void step1()
    {
        System.out.println("step1");
    }
    public void step2()
    {
        System.out.println("step2");
    }
    public void step3(String param)
    {
        System.out.println("step3");
    }

    public static void main(String[] args) {
        APITest003_NoDesignPattern np = new APITest003_NoDesignPattern();
        np.step1();
        np.step2();
        np.step3("Pramod");
        //we are directly closing the function 1 and then calling fun2 but
        //np.step1().step2().step3(); // we cannot do this in no design pattern
    }
}
