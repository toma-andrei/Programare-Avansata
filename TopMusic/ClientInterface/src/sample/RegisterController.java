package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML
    private TextField usernameText;
    @FXML
    private TextField passwordText;
    @FXML
    private CheckBox usCheck;
    @FXML
    private CheckBox roCheck;
    @FXML
    private Label serverResponse;

    public void submitButtonPressed(Event event) {
        String username = usernameText.getText();
        String password = passwordText.getText();
        boolean usCountry = usCheck.isSelected();
        boolean roCountry = roCheck.isSelected();

        Controller.inputFromUser.addElement("register");
        if (!username.equals(""))
            Controller.inputFromUser.addElement(username);


        if (!password.equals(""))
            Controller.inputFromUser.addElement(password);

        if (usCountry) {
            Controller.inputFromUser.addElement("en-US");
        } else if (roCountry) {
            Controller.inputFromUser.addElement("ro-RO");
        } else {
            Controller.inputFromUser.addElement("en-US");
        }

        String answer = Controller.writeToServer();

        if (answer.contains("creat")) {
            Controller.inputFromUser.addElement("login");
            Controller.inputFromUser.addElement(username);
            Controller.inputFromUser.addElement(password);
            answer = Controller.writeToServer().toLowerCase();
            if (answer.contains("succes")) {
                ((Node) (event.getSource())).getScene().getWindow().hide();
                Controller.changeToLoggedInMain();
            }
        }
        else{
            serverResponse.setText(answer);
        }
    }


}
