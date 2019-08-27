package com.concurrency;

/**
 * @Author: alec
 * Description: 线程间数据共享与线程安全
 *
 * @date: 11:38 2019-08-27
 */
public class ThreadDataDemo {

    public static void main(String[] args) {
       /* Thread thread01 = new Thread(new DataNoShared("线程1"));
        Thread thread02 = new Thread(new DataNoShared("线程2"));
        Thread thread03 = new Thread(new DataNoShared("线程3"));

        thread01.start();
        thread02.start();
        thread03.start();*/

        /**
         * 数据共享
         * */

        DataShared dataNoShared = new DataShared();

        Thread thread05 = new Thread(dataNoShared, "线程1");

        Thread thread06 = new Thread(dataNoShared, "线程2");

        Thread thread07 = new Thread(dataNoShared, "线程3");

        Thread thread08 = new Thread(dataNoShared, "线程4");

        Thread thread09 = new Thread(dataNoShared, "线程5");

        thread05.start();
        thread06.start();
        thread07.start();
        thread08.start();
        thread09.start();
    }

}

/**
 * 线程实现数据不共享
 * 设置为线程内部变量
 * */
class DataNoShared implements Runnable {

    public DataNoShared (String name) {
        Thread.currentThread().setName(name);
    }

    private int count = 5;

    public void run() {
        while (count > 0) {
            count --;
            System.out.println("线程 " + Thread.currentThread().getName() + " 技术Count count 值 = " + count);
        }
    }
}

class DataShared implements Runnable {
    private  int count = 5;
    public synchronized void run() {
        count --;
        System.out.println("线程 " + Thread.currentThread().getName() + " 技术Count count 值 = " + count);
    }
}