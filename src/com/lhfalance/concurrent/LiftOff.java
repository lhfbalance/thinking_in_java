package com.lhfalance.concurrent;

import java.util.concurrent.TimeUnit;

public class LiftOff implements Runnable {

  protected int countDown = 10;
  private static int taskCount = 0;
  private final int id = taskCount++;

  public LiftOff(int countDown) {
    this.countDown = countDown;
  }

  public LiftOff() {
  }

  public String status() {
    return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!") + "), ";
  }

  @Override
  public void run() {
    while(countDown-- > 0) {
      System.out.print(status());
//      Thread.yield();
//      try {
//        TimeUnit.MILLISECONDS.sleep(100);
//      } catch (InterruptedException e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//      }
    }
  }
  
  public static void main(String[] args) {
    LiftOff launch = new LiftOff();
    launch.run();
  }

}
