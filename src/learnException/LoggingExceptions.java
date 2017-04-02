package learnException;

import java.io.PrintStream;
import java.util.logging.Logger;

class LoggingException extends Exception {
  private static final long serialVersionUID = 11111L;
  
  private static Logger logger = Logger.getLogger("LoggingException");
  
  public LoggingException () {
    PrintStream s = new PrintStream(System.out);
    printStackTrace(s);
    System.out.println("");
    logger.severe(s.toString());
    
  }
}

public class LoggingExceptions {
  public static void main(String[] args) {
    try {
      throw new LoggingException();
    } catch (LoggingException e) {
      System.err.println("catch "+ e);
    }
    try {
      throw new LoggingException();
    } catch (LoggingException e) {
      System.err.println("catch "+ e);
    }
  }
}