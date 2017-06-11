package com.lhfalance.concurrent;

import java.util.concurrent.TimeUnit;

public class ADaemon implements Runnable {

  @Override
  public void run() {
    
    try {
      System.out.println("Starting ADaemon");
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      System.out.println("this should always run");
    }

  }
  
  public static void main(String[] args) {
    Thread t = new Thread(new ADaemon());
    t.setDaemon(true);
    t.start();
  }

}
