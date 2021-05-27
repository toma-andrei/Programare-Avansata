package commands.adminCommands;

import daoClasses.CommentDao;
import daoClasses.SongDao;
import entities.User;
import repositories.UserRepository;

import java.util.ResourceBundle;

public class AdminOperation {
    String[] splitCommand;
    String configFile = "res.Messages";
    ResourceBundle messages = ResourceBundle.getBundle(configFile);

    public AdminOperation(String[] splitCommand) {
        this.splitCommand = splitCommand;
    }

    public String restrictVote() {

        if (splitCommand.length != 4) {
            return messages.getString("wrongSyntax") + " \"restrict vote for <\"userId\">\".";
        }

        User user = null;
        try {
            UserRepository userRepo = new UserRepository();
            user = userRepo.findById(Integer.parseInt(splitCommand[3]));
            user.setVote(0);
            userRepo.create(user);
        } catch (Exception e) {
            return messages.getString("voteNotRestricted");
        }

        return messages.getString("voteRestricted") + " " + user.getUsername();
    }

    public String restrictComment() {

        if (splitCommand.length != 4) {
            return messages.getString("wrongSyntax") + " \"restrict comments for <\"userId\">\".";
        }

        User user = null;
        try {
            UserRepository userRepo = new UserRepository();
            user = userRepo.findById(Integer.parseInt(splitCommand[3]));
            user.setAddComment(0);
            userRepo.create(user);
        } catch (Exception e) {
            return messages.getString("commNotRestricted");
        }

        return messages.getString("commRestricted") + " " + user.getUsername();
    }

    public String restrictSongAdd() {
        if (splitCommand.length != 4) {
            return messages.getString("wrongSyntax") + " \"restrict songAdd for <\"userId\">\".";
        }

        User user = null;
        try {
            UserRepository userRepo = new UserRepository();
            user = userRepo.findById(Integer.parseInt(splitCommand[3]));
            user.setAddSong(0);
            userRepo.create(user);
        } catch (Exception e) {
            return messages.getString("songAddNotRestricted");
        }

        return messages.getString("songAddRestricted") + " " + user.getUsername();
    }

//    public String deleteSong(String id) {
//        SongDao songDao = new SongDao();
//        if (songDao.deleteSong(id)) {
//
//
//        }
//        return messages.getString("songNotDeleted");
//    }

    public String deleteComment() {
        if (splitCommand.length != 3) {
            return messages.getString("wrongSyntax") + " \"delete comment <comment_id>\"";
        }
        CommentDao commentDao = new CommentDao();
        if (commentDao.deleteById(splitCommand[2])) {
            return messages.getString("commDeleted");
        }

        return messages.getString("commNotDeleted");
    }

    public String deleteSong() {
        if (splitCommand.length != 3) {
            return messages.getString("wrongSyntax") + " \"delete song <comment_id>\"";
        }

        SongDao songDao = new SongDao();

        if (songDao.deleteSong(splitCommand[2])) {
            return messages.getString("songDeleted");
        }

        return messages.getString("songNotDeleted");

    }
}
