package com.lhfalance.concurrent;

public abstract class IntGenerator {
  private volatile boolean canceled = false;
  public abstract int next();
  public void cacel() {
    canceled = true;
  }
  public boolean isCaceled() {
    return canceled;
  }

}
