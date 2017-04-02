package com.lhfalance.concurrent;

public class EvenGenerator extends IntGenerator {
  
  private int currentEvenValue = 0;

  @Override
  public int next() {
    ++currentEvenValue;
    Thread.yield();
    ++currentEvenValue;
    return currentEvenValue;
  }

  public static void main(String[] args) {
    EventChecker.test(new EvenGenerator());

  }

}
