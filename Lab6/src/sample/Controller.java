package sample;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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

    private List<Shape> shapeList = new ArrayList<>();
    private String shapeSize;
    private String shapeColor;
    private String shapeType;
    private GraphicsContext graphicsContext;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        choiceBoxShape.getItems().addAll("Square", "Circle", "Diamond", "Snow Flake");

        graphicsContext = canvas.getGraphicsContext2D();
    }

    public void getText(ActionEvent event) {
        String textualSize = shapeSizeTextField.getText();
        System.out.println(textualSize);
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
                shapeList.add(new SquareShape(xPrim, yPrim, size));
                graphicsContext.fillRect(xPrim, yPrim, size, size);
                break;
            case "Circle":
                shapeList.add(new CircleShape(xPrim, yPrim, size));
                graphicsContext.fillOval(xPrim, yPrim, size, size);
                break;
            case "Snow Flake":
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