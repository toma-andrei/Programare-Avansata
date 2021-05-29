package sample;

import javafx.fxml.Initializable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public static final String SERVER_ADDRESS = "127.0.0.1";
    public static final int PORT = 8062;
    public static Socket socket;
    public static PrintWriter msgToServer;
    public static BufferedReader inputFromServer;

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
}
