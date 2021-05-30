package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public static final String SERVER_ADDRESS = "127.0.0.1";
    public static final int PORT = 8062;
    public static Socket socket;
    public static PrintWriter msgToServer;
    public static BufferedReader inputFromServer;
    public static Input inputFromUser = new Input();

    @FXML
    private TextArea command;
    @FXML
    private static Button loginButtonMain;
    @FXML
    private Button registerButtonMain;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            socket = new Socket(SERVER_ADDRESS, PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            msgToServer = new PrintWriter(socket.getOutputStream(), true);
            inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getCommand() {
        String userInput = command.getText();

        msgToServer.println(userInput);
        msgToServer.flush();

        String responseFromServer = null;
        try {
            responseFromServer = inputFromServer.readLine();
            responseFromServer = responseFromServer.replace("/LNSEP/", System.lineSeparator());
            System.out.println(responseFromServer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loginButtonMainPressed(Event event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("fxmlFiles/loginPage.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String writeToServer() {
        StringBuilder command = new StringBuilder("");
        for (String comm : inputFromUser.getCommand()) {
            command.append(comm).append(" ");
        }

        msgToServer.println(command);
        String answer = "";
        try {
            answer = inputFromServer.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        answer = answer.replace("/LNSEP/", System.lineSeparator());
        inputFromUser.clean();
        return answer;
    }

    public static void changeToLoggedInMain(){


        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Controller.class.getResource("fxmlFiles/mainLoggedInPage.fxml"));

        try {
            Scene scene = new Scene(fxmlLoader.load(), 800, 800);
            Stage stage = new Stage();
            stage.setTitle("Top Music");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerButtonMainPressed() {

    }

}
