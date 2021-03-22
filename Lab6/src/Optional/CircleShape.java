package Optional;

import java.util.ArrayList;
import java.util.List;

public class CircleShape implements Shape {
    List<Double> coords = new ArrayList<>();
    private double size;
    private String color;

    CircleShape(double x, double y, double size, String color) {
        coords.add(x);
        coords.add(y);
        this.size = size;
        this.color = color;
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

    @Override
    public String getColor() {
        return null;
    }
}
