package com.lhfalance.concurrent.interrupt;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class NIOBlocked implements Runnable {
  private SocketChannel sc;
  
  public NIOBlocked(SocketChannel sc) {
    
    this.sc = sc;
  }
  
  @Override
  public void run() {
    try {
      System.out.println("waiting for read() in " + this);
      sc.read(ByteBuffer.allocate(1));
    } catch (ClosedByInterruptException e) {
      System.out.println("ClosedByInterruptException");
    } catch (AsynchronousCloseException e) {
      System.out.println("AsynchronousCloseException");
    } catch (IOException e) {
      throw new RuntimeException();
    }
    System.out.println("Exiting NIOBlocking.run() " + this);
  }
}

public class NIOInterruption {
  
  public static void main(String[] args) throws Exception {
    ExecutorService exec = Executors.newCachedThreadPool();
    ServerSocket serverSocket = new ServerSocket(8090);
    InetSocketAddress isa = new InetSocketAddress("localhost", 8090);
    SocketChannel sc1 = SocketChannel.open(isa);
    SocketChannel sc2 = SocketChannel.open(isa);
    
    Future<?> future = exec.submit(new NIOBlocked(sc1));
    Future<?> future2 = exec.submit(new NIOBlocked(sc2));
//    exec.execute(new NIOBlocked(sc2));
    
    TimeUnit.SECONDS.sleep(1);
//    System.out.println("shutdon...");
//    exec.shutdown();
    future2.cancel(true);
    TimeUnit.SECONDS.sleep(1);
    System.out.println("cancel...");
    future.cancel(true);
    TimeUnit.SECONDS.sleep(1);
//    System.out.println("close...");
//    sc2.close();
    
    serverSocket.close();
    sc1.close();
    sc2.close();
    
    System.out.println( "over " );
    
  }

}
