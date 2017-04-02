package com.lhfalance.concurrent;

import java.io.IOException;

public class ResponsiveUI extends Thread{
  private volatile static double d = 1;
  public ResponsiveUI() {
    setDaemon(true);
    start();
  }
  
  @Override
  public void run() {
    while(true) {
      d = d + (Math.PI + Math.E) / d ;
    }
  }

  public static void main(String[] args) throws IOException {
    new ResponsiveUI();
    System.in.read();
    System.out.println(d);

  }

}
