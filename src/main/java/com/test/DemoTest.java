package com.test;

public class DemoTest {
    @org.junit.Test
    public void test(){
        float f = 0.1F;
        double d = 0.2;
        System.out.println(f);
        System.out.println(d);
    }
    @org.junit.Test
    public void HelloTest(){
        byte b = 1;
        HelloB helloB = new HelloB();
        String s1 ="hello";
        String s2 = "he" +"llo";
        String s3 = "he";
        String s4 = "llo";
        System.out.println(s1==s2);
        System.out.println(s1.hashCode());
        System.out.println(s1.equals(s2));
        System.out.println(s2.hashCode());
        System.out.println(s1==(s3+s4));
        System.out.println(s1.hashCode()==(s3+s4).hashCode());
        System.out.println(s1.equals(s3+s4));
        System.out.println((s3+s4).hashCode());
    }
}
