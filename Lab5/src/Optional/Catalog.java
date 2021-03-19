package Optional;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    private String name;

    public Catalog(String name) {
        this.name = name;
    }

    List<MyFile> catalogContent = new ArrayList<>();

    public void add(MyFile file) {
        try {
            for (MyFile content : catalogContent) {
                if (content.getName().equals(file.name)) {
                    throw new GeneralException("element with this name already exist");
                }
            }
            catalogContent.add(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void save(String name) {
        String fileName = name;
        fileName += ".ser";

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

    public void load(String name) {
        FileInputStream inputFile;
        try {
            inputFile = new FileInputStream(name);
            ObjectInputStream objInput = new ObjectInputStream(inputFile);
            Catalog catalog = (Catalog) objInput.readObject();
            objInput.close();

            this.name = catalog.getName();

            this.catalogContent = catalog.getCatalogContent();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getName() {
        return name;
    }

    public List<MyFile> getCatalogContent() {
        return catalogContent;
    }
}
