package com.lhfalance.concurrent;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class ExceptionThread2 implements Runnable {

  @Override
  public void run() {
    Thread t = Thread.currentThread();
    System.out.println("run() by " + t);
    System.out.println("eh = " + t.getUncaughtExceptionHandler());
    throw new RuntimeException();
  }
}

class MyUncaugtExceptionHandler implements UncaughtExceptionHandler {

  @Override
  public void uncaughtException(Thread t, Throwable e) {
    
    System.err.println("caugth " + e);
  }
  
}

class HandlerThreadFactory implements ThreadFactory {

  @Override
  public Thread newThread(Runnable r) {
    System.out.println(this + " creating a new Threading");
    Thread t = new Thread(r);
    System.out.println("create " + t);
    t.setUncaughtExceptionHandler(new MyUncaugtExceptionHandler());
    System.out.println("eh = " + t.getUncaughtExceptionHandler());
    return t;
  }
  
}

public class CaptureUncaughtException {
  

  public static void main(String[] args) {
    ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
    exec.execute(new ExceptionThread2());

  }

}
