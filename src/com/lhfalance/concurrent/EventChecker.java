package com.lhfalance.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventChecker implements Runnable {
  private IntGenerator generator;
  private final int id;
  
  public EventChecker(IntGenerator g, int ident) {
    generator = g;
    id = ident;
  }

  @Override
  public void run() {
    while(!generator.isCaceled()) {
      int val = generator.next();
      if (val % 2 != 0) {
        System.out.println(val + " not even! and current is " + Thread.currentThread());
        generator.cacel();
      }
    }
  }
  
  public static void test(IntGenerator g, int count) {
    System.out.println("Press Control-C to exit");
    ExecutorService exec = Executors.newCachedThreadPool();
//    ExecutorService exec = Executors.newFixedThreadPool(2);
    for(int i = 0; i < count; i++) {
      exec.execute(new EventChecker(g, i));
    }
    exec.shutdown();
  }
  
  public static void test(IntGenerator g) {
    test(g, 10);
  }

}
