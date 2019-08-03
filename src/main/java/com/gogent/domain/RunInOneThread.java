package com.gogent.domain;

import com.gogent.util.Utils;

public class RunInOneThread implements Runnable {

    int from;
    int to;

    public RunInOneThread(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public void run() {
        long s = System.currentTimeMillis();
        long r = Utils.getSum(from, to);
        System.out.printf("单线程从%d加到%d：%d; 耗时%dms\n", from, to, r, System.currentTimeMillis() - s);
    }
}

