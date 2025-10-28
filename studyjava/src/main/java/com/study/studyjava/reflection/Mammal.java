package com.study.studyjava.reflection;

public class Mammal {
    private int age;

    public Mammal() {

    }

    public Mammal(int age) {
        this.age = age * 2;
    }

    public String getType() {
        System.out.println("I am Mammal method getType!!!");
        return "Mammal";
    }
}
