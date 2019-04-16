package com.test;

import org.junit.Test;

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
    }
    @Test
    public void test1(){
        String s1 ="hello";
        String s2 = "he" +"llo";
        String s3 = "he";
        String s4 = "llo";
        System.out.println("“hello”==“he”+“llo”?:"+(s1==s2));
        System.out.println("“hello”.hashCode():"+s1.hashCode());
        System.out.println("“hello”.equals(“he”+“llo”)?:"+s1.equals(s2));
        System.out.println("(“he”+“llo”).hashCode():"+s2.hashCode());
        System.out.println(s1==(s3+s4));
        System.out.println(s1.hashCode()==(s3+s4).hashCode());
        System.out.println(s1.equals(s3+s4));
        System.out.println((s3+s4).hashCode());
    }
    @Test
    public void test2(){
        Boolean nomber = isNomber("102r");
        System.out.println(nomber);
    }
    /**
     * 是否是数字
     *
     * @param str
     * @return
     */
    public static Boolean isNomber(String str) {
        if (isEmpty(str)) {
            return false;
        }

        try {
            Integer temp = Integer.parseInt(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    /**
     * 空和空字符串返回true
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return null == str || "".equals(str.trim());
    }
}
