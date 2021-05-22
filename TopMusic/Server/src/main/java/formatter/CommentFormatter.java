package formatter;

import daoClasses.SongDao;
import entities.Comment;

import java.util.List;

public class CommentFormatter {

    public StringBuilder format(List<Comment> commentList, String songId) {
        StringBuilder answer = new StringBuilder("");
        SongDao songDao = new SongDao();
        answer.append("Comments for ").append(songDao.findById(songId).getName()).append("/LNSEP/");

        for (int i = 0; i < commentList.size(); i++) {
            Comment comment = commentList.get(i);
            answer.append(comment.getUsername()).append(": ").append(comment.getComment()).append("/LNSEP/");
        }

        return answer;
    }
}
