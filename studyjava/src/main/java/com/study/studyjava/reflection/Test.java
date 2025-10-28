package com.study.studyjava.reflection;

public class Test {

    public static void main(String args[]) {
        System.out.println("-----------------start------------------");
        People people = new People("zd", 36);
        System.out.println("getAge = " + people.getAge());
        System.out.println("-----------------end------------------");
    }
}
