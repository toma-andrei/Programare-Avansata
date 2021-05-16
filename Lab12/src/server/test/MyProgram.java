package server.test;

import org.testng.annotations.Test;

public class MyProgram {
    @Test
    public static void m1() {
        System.out.println("Method m1 invoked");
    }

    public static void m2() {
    }

    @Test
    public static void m3() {
        System.out.println("Method m3 invoked");
    }

    public static void m4() {
    }

    @Test
    public static void m5() {
        System.out.println("Method m5 invoked");
    }

    public static void m6() {
    }

    @Test
    public static void m7() {
        System.out.println("Method m7 invoked");
        throw new RuntimeException("Thrown from MyProgram class");
    }

    public static void m8() {
    }
}

