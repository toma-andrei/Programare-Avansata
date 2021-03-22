package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
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
import java.util.ArrayList;
import java.util.List;
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

    ToBeSaved shapes = new ToBeSaved();
    private String shapeSize;
    private String shapeColor;
    private String shapeType;
    private GraphicsContext graphicsContext;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        choiceBoxShape.getItems().addAll("Square", "Circle", "Snow Flake");

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

    public void resetButtonPressed(){
        graphicsContext.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
    }
}