package Optional;

public class SnowFlake implements Shape {
    private double xCoords;
    private double yCoords;
    private double size;
    private String color;

    public SnowFlake(double x, double y, double size, String color) {
        xCoords = x;
        yCoords = y;
        this.size = size;
        this.color = color;
    }

    @Override
    public Double getXCoords() {
        return xCoords;
    }

    @Override
    public Double getYCoords() {
        return yCoords;
    }

    @Override
    public Double getSize() {
        return size;
    }

    @Override
    public String getColor() {
        return color;
    }
}
