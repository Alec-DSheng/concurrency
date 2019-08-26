package com.concurrency;

/**
 * @Author: alec
 * Description: 线程死锁
 * @date: 16:33 2019-08-26
 */
public class DealLockDemo {

    private static String A = "A";

    private static String B = "B";

    private final int SLEEP_TIME = 2000;

    public static void main(String[] args) {

    }

    private void dealLock () {

        Thread threadA = new Thread(new Runnable() {

            public void run() {
                synchronized (A) {
                    try {
                        Thread.sleep(SLEEP_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B) {
                        System.out.println("runnable thread A");
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            public void run() {
                synchronized (B) {
                    try {
                        Thread.sleep(SLEEP_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (A) {
                        System.out.println("runnable thread B");
                    }
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}
