package com.lhfalance.generic;


public class GenericMethod {
  
  public <T> void f(T x) {
    System.out.println(x.getClass().getName());
  }
  
  public <T> T g(String string) {
    T t = (T) string;
    return t;
  }
  
  public String testg(String string) {
    return g(string);
  }

  public static void main(String[] args) {
    GenericMethod gm = new GenericMethod();
//    gm.f("");
//    gm.f(1);
//    gm.f(1.0);
//    gm.f(1.0F);
//    gm.f('c');
//    gm.f(gm);
    String a = gm.g("aaa");
    System.out.println(a);
    String b = gm.g("bbb");
    System.out.println(b);

  }

}
