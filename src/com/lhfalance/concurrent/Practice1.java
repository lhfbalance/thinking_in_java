package com.lhfalance.concurrent;

public class Practice1 implements Runnable {
  
  public Practice1() {
    System.out.println("begin Practice1");
  }

  @Override
  public void run() {
    System.out.println("in practice1");
    Thread.yield();
    System.out.println("in practice1");
    Thread.yield();
    System.out.println("in practice1");
    Thread.yield();
    System.out.println("end of practice1");
  }
  
  public static void main(String[] args) {
    for(int i = 0; i < 10; i++) {
      new Thread(new Practice1()).start();
    }
  }

}
