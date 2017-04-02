package com.lhfalance.concurrent;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class TaskWithResult implements Callable<String> {

  private int id;
  public TaskWithResult(int id) {
    this.id = id;
  }
  @Override
  public String call() throws Exception {
    return "result of TaskResult " + id;
  }
  
}

public class CallableDemo {
  public static void main(String[] args) {
    ExecutorService exec = Executors.newCachedThreadPool();
    ArrayList<Future<String>> results = new ArrayList<>();
    for (int i = 0; i < 1000; i++) {
      results.add(exec.submit(new TaskWithResult(i)));
    }
    for (Future<String> fs : results) {
      try {
        if (fs.isDone()) {
          System.out.println(fs.get());
        } else {
          System.err.println("task has not done");
        }
        
      } catch (InterruptedException e) {
        e.printStackTrace();
        return;
      } catch (ExecutionException e) {
        e.printStackTrace();
        return;
      } finally {
        exec.shutdown();
      }
      
    }
  }
}
