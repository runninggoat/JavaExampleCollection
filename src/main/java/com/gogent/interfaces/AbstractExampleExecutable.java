package com.gogent.interfaces;

public abstract class AbstractExampleExecutable implements ExampleExecutable {

    private long startTime = 0;

    private void before() {
        startTime = System.currentTimeMillis();
    }

    public abstract void process();

    private void after() {
        long span = System.currentTimeMillis() - startTime;
        System.out.println(String.format("运行用时%sms\n==========", span + ""));
    }

    public void start() {
        before();
        process();
        after();
    }

}
