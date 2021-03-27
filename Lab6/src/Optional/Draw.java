package Optional;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/***
 * cand trebuie desenata o forma, se apeleaza metoda statica respectiva.
 *          (Creeata pentru a nu polua clasa Controller cu cod repetitiv.)
 */
public class Draw {
    public static void drawSquare(double x, double y, double size, String color, GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.valueOf(color));
        graphicsContext.fillRect(x, y, size, size);
    }

    public static void drawCircle(double x, double y, double size, String color, GraphicsContext graphicsContext) {

        graphicsContext.setFill(Color.valueOf(color));
        graphicsContext.fillOval(x, y, size, size);
    }

    public static void drawSnowFlake(double x, double y, double size, String color, GraphicsContext graphicsContext){
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

    public static void drawLine(double xPrim, double yPrim, double xSecund, double ySecund, String color, GraphicsContext graphicsContext){
        graphicsContext.setStroke(Color.valueOf(color));
        graphicsContext.beginPath();
        graphicsContext.moveTo(xPrim, yPrim);
        graphicsContext.lineTo(xSecund, ySecund);
        graphicsContext.stroke();
    }
}
