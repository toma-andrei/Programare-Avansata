package sample;

import com.sun.media.jfxmediaimpl.platform.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

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
    private Button addSongButton;
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
    @FXML
    private Label serverResponse;
    @FXML
    private TextField voteText;
    @FXML
    private Button voteButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button commentsForButton;
    @FXML
    private TextField commentsForSongId;
    @FXML
    private Button addCommentButton;
    @FXML
    private TextField addCommentSongId;
    @FXML
    private TextField addCommentText;
    @FXML
    private TextField deleteSongId;
    @FXML
    private Button deleteSongButton;
    @FXML
    private Button deleteCommentButton;
    @FXML
    private TextField deleteCommentId;
    @FXML
    private TextField restrictVoteUserId;
    @FXML
    private Button restrictVoteButton;
    @FXML
    private Button restrictCommentsButton;
    @FXML
    private TextField restrictCommentsUserId;
    @FXML
    private TextField restrictSongAddUserId;
    @FXML
    private Button restrictSongAddButton;

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

        String name = addSongName.getText();
        String description = addSongDescription.getText();
        String artists = addSongArtists.getText();
        String genres = addSongGenres.getText();
        String link = addSongLink.getText();


        Controller.inputFromUser.addElement("add song");
        if (!name.equals(""))
            Controller.inputFromUser.addElement("\"" + name + "\"");
        if (!description.equals(""))
            Controller.inputFromUser.addElement("\"" + description + "\"");
        if (!artists.equals(""))
            Controller.inputFromUser.addElement("\"" + artists + "\"");
        if (!genres.equals(""))
            Controller.inputFromUser.addElement("\"" + genres + "\"");
        if (!link.equals(""))
            Controller.inputFromUser.addElement("\"" + link + "\"");

        String answer = Controller.writeToServer();
        serverResponse.setText(answer);

    }

    public void voteSong() {
        String songId = voteText.getText();

        Controller.inputFromUser.addElement("vote");
        Controller.inputFromUser.addElement(songId);
        String answer = Controller.writeToServer();

        serverResponse.setText(answer);
    }

    public void logout() {
        Controller.inputFromUser.addElement("quit");
        Controller.writeToServer();
        Controller.msgToServer.close();
    }

    public void getComments() {
        String songId = commentsForSongId.getText();
        Controller.inputFromUser.addElement("comments for");
        if (!songId.equals(""))
            Controller.inputFromUser.addElement(songId);

        String answer = Controller.writeToServer();
        textArea.clear();
        textArea.setText(answer);
    }

    public void addComment() {
        String songId = addCommentSongId.getText();
        String text = addCommentText.getText();

        Controller.inputFromUser.addElement("add comment");
        Controller.inputFromUser.addElement(songId);
        if (!text.equals(""))
            Controller.inputFromUser.addElement("\"" + text + "\"");
        String answer = Controller.writeToServer();
        serverResponse.setText(answer);
    }

    public void deleteSong() {
        String songId = deleteSongId.getText();
        Controller.inputFromUser.addElement("delete song");
        if (!songId.equals(""))
            Controller.inputFromUser.addElement(songId);

        String answer = Controller.writeToServer();
        serverResponse.setText(answer);
    }

    public void deleteComment() {
        String commentId = deleteCommentId.getText();
        Controller.inputFromUser.addElement("delete comment");
        if (!commentId.equals(""))
            Controller.inputFromUser.addElement(commentId);

        String answer = Controller.writeToServer();
        serverResponse.setText(answer);
    }

    public void restrictVote() {
        String userId = restrictVoteUserId.getText();
        Controller.inputFromUser.addElement("restrict votes for");
        if (!userId.equals(""))
            Controller.inputFromUser.addElement(userId);

        String answer = Controller.writeToServer();
        serverResponse.setText(answer);
    }

    public void restrictComments() {
        String userId = restrictCommentsUserId.getText();
        Controller.inputFromUser.addElement("restrict comments for");
        if (!userId.equals(""))
            Controller.inputFromUser.addElement(userId);

        String answer = Controller.writeToServer();
        serverResponse.setText(answer);
    }

    public void restrictSongAdd() {
        String userId = restrictSongAddUserId.getText();
        Controller.inputFromUser.addElement("restrict songAdd for");
        if (!userId.equals(""))
            Controller.inputFromUser.addElement(userId);

        String answer = Controller.writeToServer();
        serverResponse.setText(answer);
    }

}
