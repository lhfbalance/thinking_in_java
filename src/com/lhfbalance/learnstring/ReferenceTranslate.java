package com.lhfbalance.learnstring;

class A {
  int id = 0;
  public A(int id) {
    this.id = id;
  }
  
  public void introduce() {
    System.out.println("my ID is " + id);
  }
}

public class ReferenceTranslate {
  
  public static void changeId(A a) {
    a.id = 88888;
  }
  
  public static void changeReferenece(A a) {
    a = new A(66666);
  }

  public static void main(String[] args) {
    A a = new A(1);
    a.introduce();
    changeId(a);//实现了改变，因为两个引用指向同一个对象（内存区域）
    a.introduce();
    changeReferenece(a);//并没有实现改变，因为引用传递的时候是引用的值传递而已，是复制了一个引用，原来的引用并没有改变
    a.introduce();

  }

}
