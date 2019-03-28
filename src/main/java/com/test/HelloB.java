package com.test;

public class HelloB extends HelloA {

    public HelloB(){
        System.out.println("HelloB");
    }
    {
        System.out.println("I'm HelloB");
    }
    static {
        System.out.println("static HelloB");
    }
}
