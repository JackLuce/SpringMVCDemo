package com.test;

import org.junit.Test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeTest {
    @Test
    public void test(){
        //1.获取当前时间，和某个时间进行比较。此时主要拿long型的时间值。
        System.out.println(System.currentTimeMillis());
        System.out.println(new Date().getTime());
        //2.获取当前时间,   格式为:   yyyy-mm-dd   hh-mm-ss
        System.out.println(DateFormat.getDateTimeInstance(2, 2, Locale.CHINESE).format(new java.util.Date()));
        //3.获取当前时间(精确到毫秒),   格式为:   yyyy-mm-dd   hh:mm:ss.nnn
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        //4.获取当前时间,   格式为:   yyyy年mm月dd日   上午/下午hh时mm分ss秒
        System.out.println(DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG,   Locale.CHINESE).format(new java.util.Date()));
        //5. 获取当前系统时间和日期并格式化输出:
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        //6.日期转String
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(date));
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(date));
        sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        System.out.println(sdf.format(date));
        sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String string = sdf.format(date);
        System.out.println(string);
    }
}
