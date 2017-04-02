package com.lhfbalance.learnstring;

public class Immutable {
  public static String upcase(String s) {
    return s.toUpperCase();
  }
  public static void changeByte(byte[] b) {
    b[0] = 0;
    b[1] = 1;
    b[2] = 2;
    b[3] = 3;
  }
  public static void changeByte2(byte[] b) {
    byte[] a = {1, 2, 3, 4};
    b = a;
  }

  public static void main(String[] args) {
    String qString = "howdy";
    //System.out.println(qString);
    String qqString = upcase(qString);
    //System.out.println(qqString);
    //System.out.println(qString);
    
    byte[] bb = {'a', 'b', 'c', 'd'};
    for (int i = 0; i < bb.length; i++) {
      System.out.print(bb[i]);
    }
    System.out.println();
    changeByte(bb);
    for (int i = 0; i < bb.length; i++) {
      System.out.print(bb[i]);
    }
    System.out.println();
    changeByte2(bb);
    for (int i = 0; i < bb.length; i++) {
      System.out.print(bb[i]);
    }

  }

}
