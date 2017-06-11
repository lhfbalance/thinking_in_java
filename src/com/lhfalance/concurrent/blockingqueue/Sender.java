package com.lhfalance.concurrent.blockingqueue;

import java.io.PipedWriter;

public class Sender implements Runnable {
  
  private PipedWriter pw = new PipedWriter();
  
  public PipedWriter getPw() {
    return pw;
  }
  
  

  @Override
  public void run() {
    // TODO Auto-generated method stub

  }

}
