package com.concurrency;

/**
 * @Author: alec
 * Description: 线程的运行结果与执行顺序或运行顺序是无关的
 * 以下程序随机打印线程执行结果。模拟线程的随机性
 * @date: 11:27 2019-08-27
 */
public class ThreadRandom {



    public static void main(String[] args) {

        Thread randomThread001 = new RandomThread();
        randomThread001.setName("Thread001");

        Thread randomThread002 = new RandomThread();
        randomThread002.setName("Thread002");


        randomThread001.start();
        randomThread002.start();
    }

}

class RandomThread extends Thread {
    private final int SLEEP_TIME = 2000;
    @Override
    public void run() {
        for (int i = 0; i< 10; i ++) {
            try {
                Thread.sleep(SLEEP_TIME);
                System.out.println("Thread name is " + Thread.currentThread().getName() + "第 " + i + "次运行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
