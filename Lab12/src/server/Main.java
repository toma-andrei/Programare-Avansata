package server;

import org.testng.annotations.Test;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    List<String> classList = new ArrayList<>();

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        String path = "C:\\Users\\toma1\\OneDrive\\Documents\\GitHub\\Programare-Avansata\\Lab12\\out\\production\\Lab12\\server\\test\\";
        String basePath = "C:\\Users\\toma1\\OneDrive\\Documents\\GitHub\\Programare-Avansata\\Lab12\\out\\production\\Lab12\\";

        Resolver resolver = new Resolver();
        resolver.resolve(path, basePath);

    }
}
