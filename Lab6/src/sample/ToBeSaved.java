package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ToBeSaved {
    private List<Shape> shapeList = new ArrayList<>();

    public void save(String path) {
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(shapeList);
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public void add(Shape shape) {
        shapeList.add(shape);
        System.out.println(shapeList.get(0).getSize());
    }

    public void load(String path) {
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream objInput = new ObjectInputStream(fileIn);

            this.shapeList = (List<Shape>) objInput.readObject();

            fileIn.close();
            objInput.close();

            System.out.println(shapeList.size());

        } catch (IOException | ClassNotFoundException e) {
            e.getStackTrace();
        }
    }

    public List<Shape> getShapeList() {
        return shapeList;
    }
}
