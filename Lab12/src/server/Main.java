package server;

import org.testng.annotations.Test;

import javax.swing.text.ViewFactory;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        URLClassLoader loader = URLClassLoader.newInstance(new URL[]{new URL("file:///C:/Users/toma1/OneDrive/Documents/GitHub/Programare-Avansata/Lab12/out/production/Lab12/server/Test/")});
        Class clazz = loader.loadClass("server.test.MyProgram");

        System.out.print("Package name: ");
        System.out.println(clazz.getPackageName());

        System.out.print("Class name: ");
        System.out.println(clazz.getName());

        System.out.print("Class fields: ");
        System.out.println(Arrays.toString(clazz.getDeclaredFields()));

        System.out.print("Class methods: ");
        System.out.println(Arrays.toString(clazz.getMethods()));

        Method[] methods = clazz.getMethods();

        Object object = clazz.getConstructor().newInstance();

        for(Method method : methods){
            Annotation[] annotations = method.getAnnotations();

            for(Annotation annotation : annotations){
                if(annotation instanceof Test && method.getParameterCount() == 0){
                    method.invoke(object);
                }
            }
        }
    }
}
