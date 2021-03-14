package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InvalidYearException, InvalidPathException {
        Catalog myCatalog = new Catalog();

        Image copacImg = new Image("copac", "C:\\Users\\toma1\\IdeaProjects\\Lab5\\InputSources\\copac.jpg");
        Image caineImg = new Image("caine", "C:\\Users\\toma1\\IdeaProjects\\Lab5\\InputSources\\caine.jpg");
        Book cursJava = new Book("javaBook", 2014, "C:\\Users\\toma1\\IdeaProjects\\Lab5\\InputSources\\Cristian_Frasinaru-Curs_practic_de_Java.pdf");

        myCatalog.add(copacImg);
        myCatalog.add(caineImg);
        myCatalog.add(cursJava);

        myCatalog.save();
        Catalog loadedCatalog = myCatalog.load("Catalog.ser");
        loadedCatalog.add(new Book("Secretul Doctorului Honigberger", 1940, "C:\\Users\\toma1\\IdeaProjects\\Lab5\\InputSources\\secretul_doctorului_honigberger.pdf"));
        loadedCatalog.play("caine");
    }
}
