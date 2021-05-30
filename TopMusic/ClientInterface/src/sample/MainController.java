package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class MainController {
    @FXML
    private Button topGeneralButton;
    @FXML
    private TextArea textArea;
    @FXML
    private Button topForGenreButton;
    @FXML
    private TextField topForGenreText;
    @FXML
    private TextField addSongName;
    @FXML
    private TextField addSongDescription;
    @FXML
    private TextField addSongArtists;
    @FXML
    private TextField addSongGenres;
    @FXML
    private TextField addSongLink;


    public void topGeneral() {
        Controller.inputFromUser.addElement("top general");
        String answer = Controller.writeToServer();
        textArea.setText(answer);
    }


    public void topForGenre() {
        String genre = topForGenreText.getText();
        System.out.println(genre);
        if (!genre.trim().equals("")) {
            Controller.inputFromUser.addElement("top");
            Controller.inputFromUser.addElement("for");
            Controller.inputFromUser.addElement(genre);

            String answer = Controller.writeToServer();
            textArea.clear();
            textArea.setText(answer);
        }
    }

    public void addSong() {

    }

}
