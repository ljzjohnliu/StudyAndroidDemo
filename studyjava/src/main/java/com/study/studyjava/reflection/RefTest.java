package com.study.studyjava.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RefTest {
    public static void main(String args[]) {
        System.out.println("-----------------start------------------");
        People people = createPeople();
//        people.talk();
        refMethod(people);
        System.out.println("-----------------end------------------");
    }

    /**
     * getDeclaredMethod：获取当前类的所有声明的方法，包括public、protected和private修饰的方法。需要注意的是，这些方法一定是在当前类中声明的，从父类中继承的不算，实现接口的方法由于有声明所以包括在内。
     * getMethod：获取当前类和父类的所有public的方法。这里的父类，指的是继承层次中的所有父类。比如说，A继承B，B继承C，那么B和C都属于A的父类。
     */
    public static void refMethod(People people) {
        try {
            Class<?> clazz = Class.forName("com.study.studyjava.reflection.People");
            //类内部以及接口的方法反射
//            Method talkM = clazz.getDeclaredMethod("talk");
//            Method tellStoryM = clazz.getDeclaredMethod("tellStory", String.class);
//            String sss = (String) talkM.invoke(people);
//            System.out.println("refMethod, talk return is:" + sss);
//            tellStoryM.invoke(people, "new stories!!!");
//            Method getAgeM = clazz.getDeclaredMethod("getAge");
//            getAgeM.setAccessible(true);
//            int age = (int) getAgeM.invoke(people);
//            System.out.println("refMethod, getAge return is:" + age);
//            Method walkM = clazz.getDeclaredMethod("walk");
//            walkM.invoke(people);

            //父类方法反射
//            Method getTypeM = clazz.getDeclaredMethod("getType");//这个会报错因为这个方法是从父类继承来的
//            Method getTypeM = clazz.getMethod("getType");
//            String type = (String) getTypeM.invoke(people);
//            System.out.println("refMethod, getType return is:" + type);

            //静态方法反射
            Method sayHiM = clazz.getDeclaredMethod("sayHi", String.class, int.class);
            sayHiM.invoke(clazz, "zzz", 22);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 反射创建对象
     * 使用 Class.newInstance()，适用于类拥有无参构造方法
     * Constructor.newInstance()，适用于使用带参数的构造方法
     */
    public static People createPeople() {
        People people = null;
        try {
            Class<?> classType = Class.forName("com.study.studyjava.reflection.People");
//            people = (People) classType.newInstance();

//            Constructor<?> constructor = classType.getConstructor(new Class[]{String.class});
//            constructor.setAccessible(true);
//            people = (People) constructor.newInstance(new Object[]{"ljz"});

            Constructor<?> constructor2 = classType.getConstructor(String.class, int.class);
            constructor2.setAccessible(true);
            people = (People) constructor2.newInstance(new Object[]{"ljz", 40});
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                 InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return people;
    }

    /**
     * 获取 Class 对象有几种方式，有什么区别
     * 对象的getClass：new People().getClass() 适用于已知对象的情况
     * 类引用/类字面常量 People.class 适用于编译期类已知的情况，不会触发类初始化
     * Class.forName(全限定类名) 适用于已知目标类全限定名的情况，可以通过参数控制初始化&类加载器，默认触发类初始化
     */
    public static void getClassT() {
//        System.out.println("main People = " + People.class);
//        System.out.println("main People = " + new People().getClass());
        try {
            System.out.println("main People = " + Class.forName("com.study.studyjava.reflection.People"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
