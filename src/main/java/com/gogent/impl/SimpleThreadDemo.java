package com.gogent.impl;

import com.gogent.annotation.Example;
import com.gogent.domain.RunInMultiThread;
import com.gogent.domain.RunInMultiThread2;
import com.gogent.domain.RunInOneThread;
import com.gogent.interfaces.AbstractExampleExecutable;

@Example(command = "simpleThread", ref = SimpleThreadDemo.class, description = "运行一个简单的多线程演示")
public class SimpleThreadDemo extends AbstractExampleExecutable {

    int from = 1;
    int to = 2000000001;

    public void process() {
        Thread runInMultiThread = new Thread(new RunInMultiThread(from, to));
        runInMultiThread.start();
        try {
            runInMultiThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread runInMultiThread2 = new Thread(new RunInMultiThread2(from, to));
        runInMultiThread2.start();
        try {
            runInMultiThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread runInOneThread = new Thread(new RunInOneThread(from, to));
        runInOneThread.start();
        try {
            runInOneThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
