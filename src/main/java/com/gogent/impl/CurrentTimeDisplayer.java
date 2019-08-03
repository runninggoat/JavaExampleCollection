package com.gogent.impl;

import com.gogent.annotation.Example;
import com.gogent.interfaces.AbstractExampleExecutable;

import java.util.Date;

@Example(command = "now", ref = CurrentTimeDisplayer.class, description = "展示当前毫秒时间")
public class CurrentTimeDisplayer extends AbstractExampleExecutable {
    public void process() {
        Date now = getNow();
        System.out.printf("1970-01-01至当前毫秒数：\n%dms\n", now.getTime());
    }

    public Date getNow() {
//        System.out.println("call method in CurrentTimeDisplayer");
        return new Date();
    }
}
