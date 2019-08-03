package com.gogent.domain;

import com.gogent.util.Utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class RunInMultiThread2 implements Runnable {

    int from;
    int to;
    long result = 0L;

    public RunInMultiThread2(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public void run() {
        long s = System.currentTimeMillis();
        short threadNum = 4;
        int[][] groups = Utils.getGroups(from, to, threadNum);
        FutureTask<Long>[] children = new FutureTask[groups.length];
        for (int i = 0; i < groups.length; i++) {
            Callable<Long> c = new GetSumCallable(groups[i][0], groups[i][1]);
            FutureTask<Long> ft = new FutureTask<Long>(c);
            children[i] = ft;
            new Thread(ft).start();
        }
        for (FutureTask<Long> child : children) {
            try {
                mergeResult(child.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Callable实现，%d 线程从%d加到%d：%d; 耗时%dms\n", threadNum, from, to, this.result, System.currentTimeMillis() - s);
    }

    private synchronized void mergeResult(long r) {
        result += r;
    }

    class GetSumCallable implements Callable<Long> {

        int f;
        int t;

        public GetSumCallable(int f, int t) {
            this.f = f;
            this.t = t;
        }

        public Long call() throws Exception {
            long r = Utils.getSum(f, t);
//            System.out.printf("f: %d; to: %d; r: %d\n", f, t, r);
            return r;
        }
    }

}
