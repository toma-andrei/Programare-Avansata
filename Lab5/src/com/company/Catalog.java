package com.company;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    List<MyFile> catalogContent = new ArrayList<>();

    public void add(MyFile file) {
        catalogContent.add(file);
    }

    public void list() {
        System.out.println(catalogContent);
    }

    public void play(String name) {

        MyFile file = null;

        for (MyFile content : catalogContent) {

            if (content.getName().equals(name)) {
                file = content;
            }
        }

        try {
            File myFile = new File(file.getPath());
            Desktop.getDesktop().open(myFile);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void save() {
        String fileName = "Catalog.ser";
        try {
            FileOutputStream fileOutput = new FileOutputStream(fileName);

            ObjectOutputStream output = new ObjectOutputStream(fileOutput);
            output.writeObject(this);
            output.flush();
            fileOutput.close();

            System.out.println("File saved!");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Catalog load(String name) {
        String fileName = name;
        FileInputStream inputFile;
        try {
            inputFile = new FileInputStream(fileName);
            ObjectInputStream objInput = new ObjectInputStream(inputFile);
            Catalog catalog = (Catalog) objInput.readObject();
            objInput.close();
            System.out.println("File loaded!");
            return catalog;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
