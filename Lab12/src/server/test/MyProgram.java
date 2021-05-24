package server.test;

import org.testng.annotations.Test;

@Test
public class MyProgram {
    public String name;
    public Integer count;
    public int value;

    public MyProgram() {

    }

    public MyProgram(String name) {
        this.name = name;
    }

    public MyProgram(String name, int value) {
        this.value = value;
    }

    @Test
    public static void m1(int argument) {
        System.out.println("Method m1 invoked with argument " + argument);
    }

    public static void m2() {
    }

    @Test
    public void m3(String argument) {
        System.out.println("Method m3 invoked with argument " + argument);
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

