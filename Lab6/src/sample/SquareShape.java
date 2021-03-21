package sample;

import java.util.ArrayList;
import java.util.List;

public class SquareShape implements Shape {
    List<Double> coords = new ArrayList<>();
    private double size;

    SquareShape(double x, double y, double size){
        coords.add(x);
        coords.add(y);
        this.size = size;
    }

    @Override
    public Double getXCoords() {
        return coords.get(0);
    }

    @Override
    public Double getYCoords() {
        return coords.get(1);
    }

    @Override
    public Double getSize() {
        return size;
    }
}
