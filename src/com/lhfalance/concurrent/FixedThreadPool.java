package com.lhfalance.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {

  public static void main(String[] args) {
    ExecutorService exec = Executors.newFixedThreadPool(1);
    for (int i = 0; i < 10; i++) {
      exec.execute(new Practice1());
    }
    exec.shutdown();
  }

}
