package com.lhfalance.concurrent.interrupt;

import java.io.IOException;
import java.io.InputStream;

public class IOBlocked implements Runnable {

  private InputStream in;
  public IOBlocked(InputStream is) {
    in = is;
  }
  @Override
  public void run() {
    
    System.out.println("waiting to read");
    try {
      System.out.println(in.read());
    } catch (IOException e) {
      if (Thread.currentThread().isInterrupted()) {
        System.out.println("interrupted from blocked IO");
      } else {
        throw new RuntimeException();
      }
      System.out.println("exiting IOBlocked.run()");
    }
  }
  
}
