package com.lhfalance.concurrent;

import java.util.Timer;
import java.util.TimerTask;

public class Test {

  public static void main(String[] args) {
    
    Timer timer = new Timer(false);
    TimerTask m1 = new MyTimeTask("1");
    TimerTask m2 = new MyTimeTask("2");
    timer.schedule(m1, 1, 10000);
    timer.schedule(m2, 1, 10000);
    
    //System.out.println(Runtime.getRuntime().availableProcessors());
  }
  
  
}
class MyTimeTask extends TimerTask {
  
  private String id;

  public MyTimeTask(String id) {
    this.id = id;
  }
  @Override
  public void run() {

    System.out.println(Thread.currentThread().getName() + " run #" + id);
    
  }
  
}