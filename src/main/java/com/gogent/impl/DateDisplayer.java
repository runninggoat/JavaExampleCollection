package com.gogent.impl;

import com.gogent.annotation.Example;

import java.text.SimpleDateFormat;
import java.util.Date;

@Example(command = "date", ref = DateDisplayer.class, description = "展示当前的日期时间")
public class DateDisplayer extends CurrentTimeDisplayer {
    @Override
    public void process() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");
        Date now = getNow();
        System.out.printf("当前时间：\n%s\n", simpleDateFormat.format(now));
    }

    @Override
    public Date getNow() {
//        System.out.println("call method in DateDisplayer");
        return new Date();
    }
}
