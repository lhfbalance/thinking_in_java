package com.lhfalance.concurrent;

import java.util.concurrent.TimeUnit;

public class SimpleDaemons implements Runnable {

  @Override
  public void run() {
    while (true) {
      try {
        TimeUnit.MILLISECONDS.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread() + " " + this);
    }
  }

  public static void main(String[] args) throws InterruptedException {
    for(int i = 0; i < 10; i++) {
      Thread daemon = new Thread(new SimpleDaemons());
      daemon.setDaemon(true);
      daemon.start();
    }
    System.out.println("all daemon start");
    TimeUnit.MILLISECONDS.sleep(1000);

  }

}
