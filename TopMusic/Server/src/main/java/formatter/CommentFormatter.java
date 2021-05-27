package formatter;

import daoClasses.SongDao;
import entities.Comment;

import java.util.List;
import java.util.ResourceBundle;

public class CommentFormatter {

    public StringBuilder format(List<Comment> commentList, String songId, ResourceBundle messages) {
        StringBuilder answer = new StringBuilder("");
        SongDao songDao = new SongDao();
        answer.append(messages.getString("commentsFor")).append(" ").append(songDao.findById(songId).getName()).append("/LNSEP/");

        for (int i = 0; i < commentList.size(); i++) {
            Comment comment = commentList.get(i);
            answer.append(comment.getId()).append(".").append(comment.getUsername()).append(": ").append(comment.getComment()).append("/LNSEP/");
        }

        return answer;
    }
}
