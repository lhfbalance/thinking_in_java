package com.lhfalance.concurrent.blockingqueue;

import java.io.IOException;
import java.io.PipedReader;

public class Receiver implements Runnable {
  
  PipedReader pr ;

  public Receiver(Sender sender) throws IOException {
    pr = new PipedReader(sender.getPw());
  }
  @Override
  public void run() {
    // TODO Auto-generated method stub

  }

}
