package Optional;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    @FXML
    private Button undoButton;
    @FXML
    private CheckBox deleteModeCheckBox;
    @FXML
    private CheckBox shapeRecognitionCheckBox;
    @FXML
    private CheckBox square;
    @FXML
    private CheckBox circle;
    @FXML
    private CheckBox snowFlake;
    @FXML
    private CheckBox freeDraw;

    ToBeSaved shapes = new ToBeSaved();
    private String shapeSize;
    private String shapeColor;
    private String shapeType;
    private GraphicsContext graphicsContext;
    private boolean deleteModeOn;
    //ArrayList-uri de puncte (folosite pentru shape recognition)
    private List<Double> xPointsShapeRec = new ArrayList<>();
    private List<Double> yPointsShapeRec = new ArrayList<>();
    //folosit pentru shape recognition.
    private double firstAndLastMaxDistance = 50;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        graphicsContext = canvas.getGraphicsContext2D();
    }

    public void exit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    /***
     * cand butonul Done e apasat, sunt preluate informatiile din elementele de control.
     */
    public void doneButtonPressed() {
        shapeSize = shapeSizeTextField.getText();
        shapeColor = String.valueOf(colorPicker.getValue());
        deleteModeOn = deleteModeCheckBox.isSelected();

        if (square.isSelected()) {
            shapeType = "Square";
        } else if (circle.isSelected()) {
            shapeType = "Circle";
        } else if (snowFlake.isSelected()) {
            shapeType = "Snow Flake";
        } else if (freeDraw.isSelected()) {
            shapeType = "Free Draw";
        }

        System.out.println("size: " + shapeSize);
        System.out.println("color: " + shapeColor);
        System.out.println("shape: " + shapeType);
        System.out.println("delete: " + deleteModeOn);

        graphicsContext.setFill(Color.valueOf(shapeColor));
    }

    /***
     * @param event - folosit pentru a prelua coordonatele unde a avut loc evenimentul de apasare cu mouseul
     * cand a fost apasat click pe canvas, daca modul de stergere e pornit, curata canvasul si redeseneaza formele cu exceptia celui mai recent desenat in zona
     *              in care s-a apasat click. Daca modul de stergere nu e pornit, se verifica ce forma a fost selectata si se deseneaza forma respectiva cu
     *              constrangerile (culoare, forma, dimensiune) date din panoul de control.
     *
     */
    public void clickedCanvas(MouseEvent event) {
        if (deleteModeOn) {
            resetButtonPressed();
            double xCoords = event.getX();
            double yCoords = event.getY();

            int dimension = shapes.getShapeList().size();

            boolean safeToRemove = true;

            for (int i = dimension - 1; i >= 0; i--) {

                double shapeX = shapes.getShapeList().get(i).getXCoords();
                double shapeY = shapes.getShapeList().get(i).getYCoords();
                double tempShapeSize = shapes.getShapeList().get(i).getSize();

                String color = shapes.getShapeList().get(i).getColor();

                if ((!(Math.abs(shapeX - xCoords) <= tempShapeSize && Math.abs(shapeY - yCoords) <= tempShapeSize)) || !safeToRemove) {

                    if (shapes.getShapeList().get(i) instanceof SquareShape) {
                        Draw.drawSquare(shapeX, shapeY, tempShapeSize, color, graphicsContext);

                    } else if (shapes.getShapeList().get(i) instanceof CircleShape) {
                        Draw.drawCircle(shapeX, shapeY, tempShapeSize, color, graphicsContext);

                    } else if (shapes.getShapeList().get(i) instanceof SnowFlake) {
                        Draw.drawSnowFlake(shapeX, shapeY, tempShapeSize, color, graphicsContext);

                    }
                } else {
                    safeToRemove = false;
                    shapes.getShapeList().remove(i);
                }
            }
        } else {
            double xCoords = event.getX();
            double yCoords = event.getY();

            double size = (Double.parseDouble(shapeSize));

            double xPrim = xCoords - size / 2;
            double yPrim = yCoords - size / 2;

            switch (shapeType) {
                case "Square" -> {
                    shapes.add(new SquareShape(xPrim, yPrim, size, shapeColor));
                    Draw.drawSquare(xPrim, yPrim, size, shapeColor, graphicsContext);
                }
                case "Circle" -> {
                    shapes.add(new CircleShape(xPrim, yPrim, size, shapeColor));
                    Draw.drawCircle(xPrim, yPrim, size, shapeColor, graphicsContext);
                }
                case "Snow Flake" -> {
                    shapes.add(new SnowFlake(xPrim, yPrim, size, shapeColor));
                    Draw.drawSnowFlake(xPrim, yPrim, size, shapeColor, graphicsContext);
                }
            }
        }
    }

    /***
     * cand se face drag pe canvas, se apeleaza metoda aceasta si se deseneaza cercuri, cu fiecare pixel pe care s-a facut drag.
     * daca shapeRecognitionMode este on, se tin minte punctele peste care s-a facut drag.
     */
    public void freeDrawing() {

        graphicsContext.setFill(Color.valueOf(shapeColor));
        if (!shapeRecognitionCheckBox.isSelected()) {
            canvas.setOnMouseDragged(e -> {
                if (shapeType.equals("Free Draw")) {
                    graphicsContext.fillOval(e.getX(), e.getY(), Double.parseDouble(shapeSize), Double.parseDouble(shapeSize));
                }
            });
        } else {
            canvas.setOnMouseDragged(e -> {
                xPointsShapeRec.add(e.getX());
                yPointsShapeRec.add(e.getY());
            });
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

    public void resetButtonPressed() {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }


    /***
     * algoritmul de recunoastere verifica care este distanta dintre punctul de inceput si punctul de final, peste care s-au facut drag
     *          (punctele sunte retinute in metoda freeDrawing()). Daca distanta e relativ mare, deseneaza o linie intre cele doua puncte,
     *          iar daca e relativ mica, deseneaza un cerc cu centrul in centrul multimii de puncte peste care s-a facut drag, culoarea
     *          precizata in panoul de control si dimensiunea calculata in functie de multimea de puncte generata de evenimentul de drag.
     *
     *
     */
    public void recognizeShape() {
        if (shapeRecognitionCheckBox != null && shapeRecognitionCheckBox.isSelected()) {
            double xPrim = xPointsShapeRec.get(0);
            double yPrim = yPointsShapeRec.get(0);
            double xSec = xPointsShapeRec.get(xPointsShapeRec.size() - 1);
            double ySec = yPointsShapeRec.get(yPointsShapeRec.size() - 1);

            if (Math.abs(xPrim - xSec) > firstAndLastMaxDistance || Math.abs(yPrim - ySec) > firstAndLastMaxDistance) {
                Draw.drawLine(xPrim, yPrim, xSec, ySec, shapeColor, graphicsContext);
            } else {
                double minX = Integer.MAX_VALUE;
                double maxX = Integer.MIN_VALUE;
                double minY = Integer.MAX_VALUE;
                double maxY = Integer.MIN_VALUE;


                for (double pct : xPointsShapeRec) {
                    minX = Math.min(pct, minX);
                    maxX = Math.max(pct, maxX);
                }

                for (double pct : yPointsShapeRec) {
                    minY = Math.min(pct, minY);
                    maxY = Math.max(pct, maxY);
                }

                Draw.drawCircle(minX + (maxX - minX) / 2, minY + (maxY - minY) / 2, maxX - minX, shapeColor, graphicsContext);
            }

            xPointsShapeRec.clear();
            yPointsShapeRec.clear();
        }
    }

    /***
     * curata canvasul si redeseneaza formele (retinute intr-un ArrayList) cu exceptia ultimului.
     */
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
            if (shapes.getShapeList().get(i) instanceof SquareShape)
                type = "Square";
            if (shapes.getShapeList().get(i) instanceof CircleShape)
                type = "Circle";

            switch (type) {
                case "Square":
                    Draw.drawSquare(xPrim, yPrim, size, color, graphicsContext);
                    break;
                case "Circle":
                    graphicsContext.fillOval(xPrim, yPrim, size, size);
                    Draw.drawCircle(xPrim, yPrim, size, color, graphicsContext);
                    break;
                case "Snow Flake":
                    Draw.drawSnowFlake(xPrim, yPrim, size, color, graphicsContext);
                    break;
            }
        }
    }

    /***
     * schimba scena daca este selectat modul de freeDrawing. (adauga in scena noua un buton de shapeRecognition).
     */
    public void changeScene(MouseEvent event) throws IOException {
        if (freeDraw.isSelected()) {
            Parent root = FXMLLoader.load(getClass().getResource("secondScene.fxml"));
            Scene newScene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.show();
            shapes.add(new SquareShape(0, 0, 0, "#000000"));
            undoButtonPressed();
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene newScene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.show();
            shapes.add(new SquareShape(0, 0, 0, "#000000"));
            undoButtonPressed();
        }
    }
}