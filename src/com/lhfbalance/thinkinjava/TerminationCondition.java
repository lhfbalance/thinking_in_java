package com.lhfbalance.thinkinjava;

class Book {
  boolean checkedOut = false;
  public Book(boolean checkout) {
    this.checkedOut = checkout;
  }
  
  void checkIn() {
    this.checkedOut = false;
  }
  
  
  protected void finallize() throws Throwable {
    if (checkedOut) {
      System.out.println("Error: checked out");
      //super.finalize();
    }
  }
}

public class TerminationCondition {

  public static void main(String[] args) {

    Book novel = new Book(true);
    novel.checkIn();
    new Book(true);
    
    System.gc();
  }

}
