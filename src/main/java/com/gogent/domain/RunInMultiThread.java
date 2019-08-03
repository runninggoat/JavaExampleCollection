package com.gogent.domain;

import com.gogent.util.Utils;

public class RunInMultiThread implements Runnable {

    int from;
    int to;
    long result = 0L;
    int awaitNum = 0;

    public RunInMultiThread(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public void run() {
        long s = System.currentTimeMillis();
        short threadNum = 4;
        int[][] groups = Utils.getGroups(from, to, threadNum);
        Thread[] children = new Thread[groups.length];
        awaitNum = groups.length;
        for (int i = 0; i < groups.length; i++) {
            children[i] = new Thread(new GetSumThread(groups[i][0], groups[i][1]));
            children[i].start();
        }
        while (awaitNum > 0) {
            try {
                Thread.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("%d 线程从%d加到%d：%d; 耗时%dms\n", threadNum, from, to, this.result, System.currentTimeMillis() - s);
    }

    private synchronized void mergeResult(long r) {
        result += r;
        --awaitNum;
    }

    class GetSumThread implements Runnable {

        int f;
        int t;

        public GetSumThread(int f, int t) {
            this.f = f;
            this.t = t;
        }

        public void run() {
            long r = Utils.getSum(f, t);
//            System.out.printf("f: %d; to: %d; r: %d\n", f, t, r);
            mergeResult(r);
        }

    }

}

