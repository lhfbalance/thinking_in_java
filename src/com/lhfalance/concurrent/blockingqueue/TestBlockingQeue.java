package com.lhfalance.concurrent.blockingqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

import com.lhfalance.concurrent.LiftOff;

class LiftOffRunner implements Runnable {

  private BlockingQueue<LiftOff> rockets;

  public LiftOffRunner(BlockingQueue<LiftOff> queue) {
    this.rockets = queue;
  }

  public void add(LiftOff lo) {
    try {
      rockets.put(lo);
    } catch (InterruptedException e) {
      System.out.println("interrupt from put()");
    }
  }

  @Override
  public void run() {
    try {
      while (!Thread.interrupted()) {
        LiftOff rocket = rockets.take();
        rocket.run();
      }
    } catch (Exception e) {
      System.out.println("interrupt from take()");
    }
    System.out.println("exiting LiftOffRunner");
  }

}

public class TestBlockingQeue{
  
  static void getKey() {
    try {
      new BufferedReader(new InputStreamReader(System.in)).readLine();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      throw new RuntimeException();
    }
  }
  
  static void getKey(String msg) {
    System.out.println(msg);
    getKey();
  }
  
  static void test(String msg, BlockingQueue<LiftOff> queue) {
    System.out.println(msg);
    LiftOffRunner runner = new LiftOffRunner(queue);
    Thread thread = new Thread(runner);
    thread.start();
    for(int i = 0; i<5; i++)
      runner.add(new LiftOff(5));
    getKey("press 'enter' (" + msg + ")");
    thread.interrupt();
    System.out.println("Finish" + msg + "test");
  } 
  
  public static void main(String[] args) {
    test("LinkedBlockingQueue", new LinkedBlockingQueue<>());
    test("ArrayBlockingQueue", new ArrayBlockingQueue<>(5));
    test("SynchronousQueue", new SynchronousQueue<>());
  }
  
  
}
