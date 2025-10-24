package com.study.studyjava;

public class TestFor {
    public static void main(String args[]) {
        System.out.println("Hello World!");
//        testFor1();
//        testFor2();
//        testFor3();
        testFor4();
    }

    /**
     * 1. 使用标签（Label）跳出指定循环（推荐）
     */
    private static void testFor1() {
        int outSize = 8;
        int innerSize = 5;
        outerLoop:
        for (int i = 0; i < outSize; i++) {
            System.out.println("-------------------------------i = " + i);
            for (int j = 0; j < innerSize; j++) {
                System.out.println("**************j = " + j);
                if (i == 2 && j == 3) {
                    System.out.println("================================================j = " + j);
                    break outerLoop;
                }
            }
        }
        System.out.println("&&&&&&&&&&&&&&&&&&&   end   &&&&&&&&&&&&&&&&&&&&&");
    }

    /**
     * 2. 使用标志变量控制外层循环
     */
    private static void testFor2() {
        int outSize = 8;
        int innerSize = 5;
        boolean found = false;
        for (int i = 0; i < outSize && !found; i++) {
            System.out.println("-------------------------------i = " + i);
            for (int j = 0; j < innerSize && !found; j++) {
                System.out.println("**************j = " + j);
                if (i == 2 && j == 3) {
                    System.out.println("================================================j = " + j);
                    found = true;
                }
            }
        }
        System.out.println("&&&&&&&&&&&&&&&&&&&   end   &&&&&&&&&&&&&&&&&&&&&");
    }

    /**
     * 3. 使用 return 直接退出方法
     * 弊端没法执行到后续代码
     */
    private static void testFor3() {
        int outSize = 8;
        int innerSize = 5;
        for (int i = 0; i < outSize; i++) {
            System.out.println("-------------------------------i = " + i);
            for (int j = 0; j < innerSize; j++) {
                System.out.println("**************j = " + j);
                if (i == 2 && j == 3) {
                    System.out.println("================================================j = " + j);
                    return;
                }
            }
        }
        System.out.println("&&&&&&&&&&&&&&&&&&&   end   &&&&&&&&&&&&&&&&&&&&&");
    }

    /**
     * 4. 使用异常（不推荐，仅特殊情况）
     */
    private static void testFor4() {
        int outSize = 8;
        int innerSize = 5;
        try {
            for (int i = 0; i < outSize; i++) {
                System.out.println("-------------------------------i = " + i);
                for (int j = 0; j < innerSize; j++) {
                    System.out.println("**************j = " + j);
                    if (i == 2 && j == 3) {
                        System.out.println("================================================j = " + j);
                        throw new BreakLoopException();
                    }
                }
            }
        } catch (BreakLoopException e) {
            System.out.println("------------BreakLoopException = " + e);
        }
        System.out.println("&&&&&&&&&&&&&&&&&&&   end   &&&&&&&&&&&&&&&&&&&&&");
    }

    // 自定义异常
    static class BreakLoopException extends RuntimeException {
    }
}
