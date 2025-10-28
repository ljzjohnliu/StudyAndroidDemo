package com.study.studyjava.reflection;

public class People extends Mammal implements Walkable {
    private static String country;
    private String name;
    private int age;
    static {
        System.out.println("I will init country");
        country = "China";
    }

    public People() {
        this("zhangsan", 30);
        System.out.println("People, constructor ----111---");
    }

    public People(String name) {
        this(name, 30);
        System.out.println("People, constructor ----222---");
    }

    public People(String name, int age) {
        super(age);
        this.name = name;
        this.age = age;
        System.out.println("People, constructor ----333---");
        System.out.println("I am People, name is:" + name + ", age is:" + age);
    }

    public String talk() {
        String ss = "I will talk, My name is:" + name + ", age is:" + age;
        System.out.println(ss);
        return ss;
    }

    public String tellStory(String story) {
        String ss = "I will tellStory: " + story;
        System.out.println(ss);
        return ss;
    }

    public int getAge() {
        System.out.println("I am People method getAge!!!");
        return age;
    }

    private void secret() {
        System.out.println("I am People private method secret!!!");
    }

//    @Override
//    public String getType() {
//        System.out.println("I am People method getType!!!");
//        return super.getType();
//    }

    public static void sayHi(String name, int age){
        System.out.println("大家好，我叫" + name + "，今年" + age + "岁" + ", from " + country);
    }

    @Override
    public void walk() {
        System.out.println("I can walk!!!");
    }
}
