package commands;

import entities.User;
import repositories.UserRepository;

public class AdminOperation {
    String[] splitCommand;

    public AdminOperation(String[] splitCommand) {
        this.splitCommand = splitCommand;
    }

    public String restrictVote() {
        if (splitCommand.length != 4) {
            return "Please use syntax \"restrict vote for <\"userId\">\".";
        }

        User user = null;
        try {
            UserRepository userRepo = new UserRepository();
            user = userRepo.findById(Integer.parseInt(splitCommand[3]));
            user.setVote(0);
            userRepo.create(user);
        } catch (Exception e) {
            return "Vote couldn't be restricted.";
        }

        return "Vote restricted for user " + user.getUsername();
    }

    public String restrictComment() {
        if (splitCommand.length != 4) {
            return "Please use syntax \"restrict comments for <\"userId\">\".";
        }

        User user = null;
        try {
            UserRepository userRepo = new UserRepository();
            user = userRepo.findById(Integer.parseInt(splitCommand[3]));
            user.setAddComment(0);
            userRepo.create(user);
        } catch (Exception e) {
            return "Comments couldn't be restricted.";
        }

        return "Comments restricted for user " + user.getUsername();
    }

    public String restrictSongAdd(){
        if (splitCommand.length != 4) {
            return "Please use syntax \"restrict songAdd for <\"userId\">\".";
        }

        User user = null;
        try {
            UserRepository userRepo = new UserRepository();
            user = userRepo.findById(Integer.parseInt(splitCommand[3]));
            user.setAddSong(0);
            userRepo.create(user);
        } catch (Exception e) {
            return "Adding songs couldn't be restricted.";
        }

        return "Adding songs restricted for user " + user.getUsername();
    }
}
