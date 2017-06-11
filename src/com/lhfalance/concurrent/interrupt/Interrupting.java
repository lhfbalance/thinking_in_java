package com.lhfalance.concurrent.interrupt;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Interrupting {

  private static ExecutorService exec = Executors.newCachedThreadPool();

  static void test(Runnable task) throws InterruptedException {
    Future<?> future = exec.submit(task);
    TimeUnit.MILLISECONDS.sleep(100);
    System.out.println("Interrupting " + task.getClass().getName());
    future.cancel(true);
    System.out.println("Interrupt sent to " + task.getClass().getName());
  }

  static Thread creatThreadAndStart(Runnable task) {
    Thread t = new Thread(task, task.getClass().getName());
    t.start();
    return t;
  }

  public static void main(String[] args) throws InterruptedException, IOException {
    InputStream in = System.in;
    test(new SleepBlocked());
    test(new IOBlocked(in));
    test(new SynchronizedBlocked());

    // Thread t1 = creatThreadAndStart(new SleepBlocked());
    // Thread t2 = creatThreadAndStart(new IOBlocked(System.in));
    // Thread t3 = creatThreadAndStart(new SynchronizedBlocked());
    // TimeUnit.SECONDS.sleep(1);
    // t1.interrupt();
    // t2.interrupt();
    // t3.interrupt();

    TimeUnit.SECONDS.sleep(3);
    System.out.println("exiting....");

    System.exit(0);
  }

}
