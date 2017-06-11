package com.lhfalance.concurrent.waitnotify;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Blocker {
  synchronized void waitingCall() {
    try {
      while (!Thread.interrupted()) {
        wait();
        System.out.println(Thread.currentThread() + " notified");
      }
    } catch (InterruptedException e) {
      System.out.println("exiting via InterruptedException");
    }
  }

  synchronized void prod() {
    notify();
  }

  synchronized void prodAll() {
    notifyAll();
  }
}


class Task implements Runnable {
  static Blocker blocker = new Blocker();

  @Override
  public void run() {
    blocker.waitingCall();
  }
}


class Task2 implements Runnable {
  static Blocker blocker = new Blocker();

  @Override
  public void run() {
    blocker.waitingCall();
  }
}


public class NotifyVsNotifyAll {
  public static void main(String[] args) throws InterruptedException {

    ExecutorService exec = Executors.newCachedThreadPool();
    for(int i=0; i<5; i++) {
      exec.execute(new Task());
    }
    exec.execute(new Task2());
    
    Timer timer = new Timer();
    
    timer.scheduleAtFixedRate(new TimerTask() {
      boolean prod = true;
      @Override
      public void run() {
        if (prod) {
          System.out.println("notify()");
          Task.blocker.prod();
          prod = false;
        } else {
          System.out.println("notifyAll");
          Task.blocker.prodAll();
          prod = true;
        }
        
      }
    }, 400, 400);
    
    TimeUnit.MILLISECONDS.sleep(3000);
    timer.cancel();
    System.out.println("canceled...");
    TimeUnit.MILLISECONDS.sleep(500);
    Task2.blocker.prodAll();
    TimeUnit.MILLISECONDS.sleep(500);
    
//    System.out.println("shutdown...");
//    exec.shutdown();
//    TimeUnit.MILLISECONDS.sleep(1000);
    
    System.out.println("shutdownNow...");
    exec.shutdownNow();
  }
}
