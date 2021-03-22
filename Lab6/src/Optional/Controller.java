package Optional;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField shapeSizeTextField;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Button doneButton;
    @FXML
    private ChoiceBox choiceBoxShape;
    @FXML
    private Canvas canvas;
    @FXML
    private Button exitButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button loadButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button undoButton;
    @FXML
    private CheckBox deleteModeCheckBox;

    ToBeSaved shapes = new ToBeSaved();
    private String shapeSize;
    private String shapeColor;
    private String shapeType;
    private GraphicsContext graphicsContext;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        choiceBoxShape.getItems().addAll("Square", "Circle", "Snow Flake", "Free Draw");

        graphicsContext = canvas.getGraphicsContext2D();
    }

    public void exit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void doneButtonPressed() {
        shapeSize = shapeSizeTextField.getText();
        shapeType = (String) choiceBoxShape.getValue();
        shapeColor = String.valueOf(colorPicker.getValue());

        System.out.println("size: " + shapeSize);
        System.out.println("color: " + shapeColor);
        System.out.println("shape: " + shapeType);

        graphicsContext.setFill(Color.valueOf(shapeColor));
    }

    public void clickedCanvas(MouseEvent event) {

        if (deleteModeCheckBox.isSelected()) {
            resetButtonPressed();
            double xCoords = event.getX();
            double yCoords = event.getY();

            int dimension = shapes.getShapeList().size();

            for (int i = dimension - 1; i >= 0; i++) {
                double shapeX = shapes.getShapeList().get(i).getXCoords();
                double shapeY = shapes.getShapeList().get(i).getYCoords();
                double tempShapeSize = shapes.getShapeList().get(i).getSize();
                if (!(Math.abs(shapeX - xCoords) <= tempShapeSize && Math.abs(shapeY - yCoords) <= tempShapeSize)) {
                    ....
                }
            }
        } else {
            double xCoords = event.getX();
            double yCoords = event.getY();

            double size = (Double.parseDouble(shapeSize));

            double xPrim = xCoords - size / 2;
            double yPrim = yCoords - size / 2;

            switch (shapeType) {
                case "Square":
                    shapes.add(new SquareShape(xPrim, yPrim, size, shapeColor));
                    graphicsContext.fillRect(xPrim, yPrim, size, size);
                    break;
                case "Circle":
                    shapes.add(new CircleShape(xPrim, yPrim, size, shapeColor));
                    graphicsContext.fillOval(xPrim, yPrim, size, size);
                    break;
                case "Snow Flake":
                    shapes.add(new SnowFlake(xPrim, yPrim, size, shapeColor));
                    graphicsContext.setStroke(Color.valueOf(shapeColor));
                    graphicsContext.beginPath();
                    graphicsContext.moveTo(xPrim, yPrim);
                    graphicsContext.lineTo(xPrim, yPrim - size);
                    graphicsContext.moveTo(xPrim, yPrim);
                    graphicsContext.lineTo(xPrim + size, yPrim);
                    graphicsContext.moveTo(xPrim, yPrim);
                    graphicsContext.lineTo(xPrim - size, yPrim);
                    graphicsContext.moveTo(xPrim, yPrim);
                    graphicsContext.lineTo(xPrim, yPrim + size);
                    graphicsContext.moveTo(xPrim, yPrim);
                    graphicsContext.lineTo(xPrim + size, yPrim + size);
                    graphicsContext.moveTo(xPrim, yPrim);
                    graphicsContext.lineTo(xPrim - size, yPrim + size);
                    graphicsContext.moveTo(xPrim, yPrim);
                    graphicsContext.lineTo(xPrim - size, yPrim - size);
                    graphicsContext.moveTo(xPrim, yPrim);
                    graphicsContext.lineTo(xPrim + size, yPrim - size);
                    graphicsContext.stroke();
                    break;
            }
        }
    }

    public void freeDrawing() {
        graphicsContext.setFill(Color.valueOf(shapeColor));
        canvas.setOnMouseDragged(e -> {
            if (shapeType.equals("Free Draw")) {
                graphicsContext.fillOval(e.getX(), e.getY(), Double.parseDouble(shapeSize), Double.parseDouble(shapeSize));
            }
        });
    }

    public void saveButtonPressed() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        Stage stage = new Stage();
        File selectedFile = fileChooser.showSaveDialog(stage);

        try {
            WritableImage writImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());

            canvas.snapshot(null, writImage);

            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writImage, null);

            ImageIO.write(renderedImage, "png", selectedFile);

        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public void loadButtonPressed() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        Stage stage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(stage);
        try {
            Image image = new Image(new FileInputStream(selectedFile.getPath()));
            graphicsContext.drawImage(image, 0, 0);
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        }
    }

    public void resetButtonPressed() {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void undoButtonPressed() {
        resetButtonPressed();

        int dimension = shapes.getShapeList().size();

        if (dimension == 0)
            return;

        shapes.getShapeList().remove(dimension - 1);

        for (int i = 0; i < dimension - 1; i++) {
            double xPrim = shapes.getShapeList().get(i).getXCoords();
            double yPrim = shapes.getShapeList().get(i).getYCoords();
            String color = shapes.getShapeList().get(i).getColor();
            double size = shapes.getShapeList().get(i).getSize();
            String type = "Snow Flake";

            if (shapes.getShapeList().get(0) instanceof SquareShape)
                type = "Square";
            if (shapes.getShapeList().get(0) instanceof CircleShape)
                type = "Circle";

            switch (type) {
                case "Square":
                    graphicsContext.fillRect(xPrim, yPrim, size, size);
                    break;
                case "Circle":
                    graphicsContext.fillOval(xPrim, yPrim, size, size);
                    break;
                case "Snow Flake":
                    graphicsContext.setStroke(Color.valueOf(color));
                    graphicsContext.beginPath();
                    graphicsContext.moveTo(xPrim, yPrim);
                    graphicsContext.lineTo(xPrim, yPrim - size);
                    graphicsContext.moveTo(xPrim, yPrim);
                    graphicsContext.lineTo(xPrim + size, yPrim);
                    graphicsContext.moveTo(xPrim, yPrim);
                    graphicsContext.lineTo(xPrim - size, yPrim);
                    graphicsContext.moveTo(xPrim, yPrim);
                    graphicsContext.lineTo(xPrim, yPrim + size);
                    graphicsContext.moveTo(xPrim, yPrim);
                    graphicsContext.lineTo(xPrim + size, yPrim + size);
                    graphicsContext.moveTo(xPrim, yPrim);
                    graphicsContext.lineTo(xPrim - size, yPrim + size);
                    graphicsContext.moveTo(xPrim, yPrim);
                    graphicsContext.lineTo(xPrim - size, yPrim - size);
                    graphicsContext.moveTo(xPrim, yPrim);
                    graphicsContext.lineTo(xPrim + size, yPrim - size);
                    graphicsContext.stroke();
                    break;
            }
        }
    }
}