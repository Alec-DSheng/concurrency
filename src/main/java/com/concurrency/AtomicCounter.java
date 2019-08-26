package com.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: alec
 * Description: 实现原子操作
 * Java实现原子操作的方法有 锁 和 循环CAS
 * 本实例使用循环CAS 实现原子操作
 * @date: 17:50 2019-08-26
 */
public class AtomicCounter {

    private int count = 0;

    private AtomicInteger atomicCount =  new AtomicInteger(0);

    public static void main(String[] args) {

       final AtomicCounter atomicCounter =  new AtomicCounter();
       atomicCounter.sumCount();
    }


    private void sumCount () {



        List<Thread> threads = new ArrayList<Thread>();

        long currentTime = System.currentTimeMillis();

        for (int i = 0; i < 100; i ++) {
            threads.add(new Thread(new Runnable() {
                public void run() {
                    for (int j = 0 ; j< 10000; j ++) {
                        count();
                        salfCount();
                    }
                }
            }));
        }


        for (Thread thread: threads) {
            thread.start();
        }

        for (Thread thread: threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(this.count);
        System.out.println(this.atomicCount.get());
        System.out.println(System.currentTimeMillis() - currentTime);
    }

    /**
     * 非线程安全的技术方式
     * */
    private void count () {

        count ++;
    }
    /**
     * 使用CAS实现线程安全的计数
     * */
    private void salfCount() {
        for (;;) {
           int value = atomicCount.get();
           boolean suc = atomicCount.compareAndSet(value, ++value);
           if (suc) {
               break;
           }
        }
    }
}
