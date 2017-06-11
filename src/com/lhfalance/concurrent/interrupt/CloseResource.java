package com.lhfalance.concurrent.interrupt;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CloseResource {
  
  public static void main(String[] args) throws Exception {
    ExecutorService exec = Executors.newCachedThreadPool();
    ServerSocket serverSocket = new ServerSocket(8090);
    InputStream socketInput = new Socket("localhost", 8090).getInputStream();
    
    exec.execute(new IOBlocked(socketInput));
    //exec.execute(new IOBlocked(System.in));
    TimeUnit.MILLISECONDS.sleep(100);
    System.out.println("shutdown all thread");
    exec.shutdownNow();
    TimeUnit.SECONDS.sleep(1);
    
    System.out.println("closing...sockeInput");
    //socketInput.close();
    TimeUnit.SECONDS.sleep(1);
    System.out.println("closing...intput");
    //System.in.close();
    
    System.out.println(".....");
  }

}
