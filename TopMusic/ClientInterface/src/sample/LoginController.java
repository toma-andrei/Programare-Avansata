package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private static Button submitButton;

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    private Label inputResult;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void submitButtonPressed(Event event){
        String usernameValue = username.getText();
        String passwordValue = password.getText();

        Controller.inputFromUser.addElement("login");
        Controller.inputFromUser.addElement(usernameValue);
        Controller.inputFromUser.addElement(passwordValue);
        String answer = Controller.writeToServer().toLowerCase();
        if(answer.contains("succes")){
            ((Node)(event.getSource())).getScene().getWindow().hide();
            Controller.changeToLoggedInMain();
        }
        else{
            inputResult.setText(answer);
        }
    }
}
