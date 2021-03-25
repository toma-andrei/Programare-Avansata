package Optional;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Draw {
    static void drawSquare(double x, double y, double size, String color, GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.valueOf(color));
        graphicsContext.fillRect(x, y, size, size);
    }

    static void drawCircle(double x, double y, double size, String color, GraphicsContext graphicsContext) {

        graphicsContext.setFill(Color.valueOf(color));
        graphicsContext.fillOval(x, y, size, size);
    }

    static void drawSnowFlake(double x, double y, double size, String color, GraphicsContext graphicsContext){
        graphicsContext.setStroke(Color.valueOf(color));
        graphicsContext.beginPath();
        graphicsContext.moveTo(x, y);
        graphicsContext.lineTo(x, y - size);
        graphicsContext.moveTo(x, y);
        graphicsContext.lineTo(x + size, y);
        graphicsContext.moveTo(x, y);
        graphicsContext.lineTo(x - size, y);
        graphicsContext.moveTo(x, y);
        graphicsContext.lineTo(x, y + size);
        graphicsContext.moveTo(x, y);
        graphicsContext.lineTo(x + size, y + size);
        graphicsContext.moveTo(x, y);
        graphicsContext.lineTo(x - size, y + size);
        graphicsContext.moveTo(x, y);
        graphicsContext.lineTo(x - size, y - size);
        graphicsContext.moveTo(x, y);
        graphicsContext.lineTo(x + size, y - size);
        graphicsContext.stroke();
    }
}
