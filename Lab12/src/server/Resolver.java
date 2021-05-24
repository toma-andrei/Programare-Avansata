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
import java.util.List;
import java.util.Random;

public class Resolver {
    List<String> classList = new ArrayList<>();
    List<Class> objectList = new ArrayList<>();
    List<String> classNames = new ArrayList<>();

    int annotatedClasses = 0;
    int notAnnotatedClasses = 0;
    int totalTestMethods = 0;
    int passedTests = 0;
    int notPassedTests = 0;

    public void resolve(String path, String basePath) throws MalformedURLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        search(new File(path));
        URLClassLoader loader;

        for (String link : classList) {

            String className = link.replace(basePath, "");
            String currentPath;

            int index = link.lastIndexOf("\\");
            currentPath = link.substring(0, index + 1).replace("\\", "/");

            className = className.replace("\\", ".");
            className = className.replace(".class", "");

            loader = URLClassLoader.newInstance(new URL[]{new URL("file:///" + currentPath)});
            objectList.add(loader.loadClass(className));
            classNames.add(className);
        }

        for (Class clazz : objectList) {

            Annotation[] ann = clazz.getAnnotations();

            for (Annotation annot : ann) {
                if (annot instanceof Test) {
                    annotatedClasses++;
                    System.out.println("CLASS NAME: " + classNames.get(objectList.indexOf(clazz)));
                    JavapToolProto javapTool = new JavapToolProto();
                    javapTool.execute(clazz);

                    Method[] methods = clazz.getMethods();

                    Constructor[] constructors = clazz.getDeclaredConstructors();

                    Object object = null;

                    for (Constructor c : constructors) {
                        if (c.getParameterCount() == 0) {
                            object = c.newInstance();
                        }
                    }

                    if (object == null) {
                        System.out.println("No such constructor!");
                        return;
                    }


                    for (Method method : methods) {
                        Annotation[] annotations = method.getAnnotations();

                        for (Annotation annotation : annotations) {
                            if (annotation instanceof Test) {
                                totalTestMethods++;
                                if (method.getParameterCount() == 1) {
                                    for (Class<?> param : method.getParameterTypes())
                                        if (param.toString().equals("int")) {
                                            try {
                                                method.invoke(object, generateInt());
                                                passedTests++;
                                            } catch (Exception e) {
                                                notPassedTests++;
                                            }

                                        } else {
                                            try {

                                                method.invoke(object, String.valueOf(generateInt()));
                                                passedTests++;
                                            } catch (Exception e) {
                                                notPassedTests++;
                                            }
                                        }
                                } else {
                                    try {
                                        method.invoke(object);
                                        passedTests++;
                                    } catch (Exception e) {
                                        notPassedTests++;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    notAnnotatedClasses++;
                }
            }

        }

        System.out.println();
        System.out.println("Total number of annotated classes: " + annotatedClasses);
        System.out.println("Total number of not annotated classes: " + (classNames.size() - annotatedClasses));
        System.out.println("Total number of annotated methods: " + totalTestMethods);
        System.out.println("Total number of passed tests: " + passedTests);
        System.out.println("Total number of tests not passed: " + notPassedTests);
    }


    private void search(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                search(f);
            }
        } else {
            classList.add(file.toString());
        }
    }

    private static int generateInt() {
        Random random = new Random();
        int upperBound = 300;
        return random.nextInt(upperBound);
    }
}
