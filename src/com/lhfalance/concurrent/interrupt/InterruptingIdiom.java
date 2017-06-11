package com.lhfalance.concurrent.interrupt;

import java.util.concurrent.TimeUnit;

class NeedsCleanUp {
  
  private final int id ;
  
  public NeedsCleanUp(int ident) {
    id = ident;
    System.out.println("needsCleanUp" + id);
  }
  
  public void cleanup() {
    System.out.println("cleaning up" + id);
  }
}

class Blocked3 implements Runnable {
  private volatile double d = 0.0;
  @Override
  public void run() {
    try {
      while(!Thread.interrupted()) {
        NeedsCleanUp n1 = new NeedsCleanUp(1);
        try {
          System.out.println("sleeping");
          TimeUnit.SECONDS.sleep(1);
          
          NeedsCleanUp n2 = new NeedsCleanUp(2);
          try {
            System.out.println("calculating");
            for(int i = 0; i < Integer.MAX_VALUE; i ++) {
              d = d + (Math.PI + Math.E) /d;
            }
            System.out.println("finish time-cosuming operation");
          } finally {
            n2.cleanup();
          }
        } finally {
          n1.cleanup();
        }
      }
      System.out.println("exiting while()");
    } catch (InterruptedException e) {
      System.out.println("exiting via InterruptedException");
    }
  }
}

public class InterruptingIdiom {
  
  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(new Blocked3());
    thread.start();
    TimeUnit.MILLISECONDS.sleep(500);
    thread.interrupt();
  }

}
