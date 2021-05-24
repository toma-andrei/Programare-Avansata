package server;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class JavapToolProto {
    public void execute(Class clazz, String... args) {
        if (args.length == 0) {
            System.out.println("FIELDS:");
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                System.out.println("   " + field);
            }

            System.out.println();
            System.out.println("CONSTRUCTORS: ");
            Constructor[] constructors = clazz.getDeclaredConstructors();

            for (Constructor constructor : constructors) {
                System.out.println("   " + constructor);
            }

            System.out.println();
            System.out.println("METHODS: ");
            Method[] methods = clazz.getDeclaredMethods();

            for (Method m : methods) {
                System.out.println("   " + m);
            }
        } else {
            for (String arg : args) {
                if (arg.equals("-package")) {
                    System.out.println("PACKAGE: ");
                    System.out.println("   " + clazz.getPackageName());
                    System.out.println();
                }
            }
        }
        System.out.println();

    }
}
