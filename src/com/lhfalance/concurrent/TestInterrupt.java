package com.lhfalance.concurrent;

import java.util.concurrent.TimeUnit;

public class TestInterrupt {

  public static void main(String[] args) throws InterruptedException {
    Thread t1 = new Thread(() -> {
        while(true) {
            try {
              System.in.read();
            } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
            System.out.println(Thread.interrupted());
            System.out.println(Thread.currentThread().isInterrupted());
        }
    }, "t1");

    t1.start();

    TimeUnit.SECONDS.sleep(1);

    System.out.println("interrupt");
    t1.interrupt();
    
    TimeUnit.SECONDS.sleep(100);



  }



}
