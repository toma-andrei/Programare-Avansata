package commands.userCommands;

import daoClasses.CommentDao;
import daoClasses.SongDao;
import entities.*;
import formatter.CommentFormatter;
import formatter.SongFormatter;
import repositories.UserRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class UserOperation {

    String[] splitCommand;

    public UserOperation(String[] splitCommand) {
        this.splitCommand = splitCommand;
    }

    public String register() {
        UserRepository userRepo = new UserRepository();
        if (splitCommand.length == 3) {
            if (!UserRepository.usernameExists(splitCommand[1])) {
                User user = new User();
                user.setUsername(splitCommand[1]);
                user.setPassword(generateHashedPassword(splitCommand[2]));
                userRepo.create(user);
                return "Congratulations, your account has been successfully created.";
            } else {
                return "This username is already taken. Please choose another name.";
            }
        } else {
            return "Please use syntax \"register <username> <password>\".";
        }
    }

    public String login() {
        UserRepository userRepo = new UserRepository();
        if (splitCommand.length == 3) {
            if (userRepo.correctLoginInput(splitCommand[1], generateHashedPassword(splitCommand[2]))) {
                return "Login Successful.";
            } else {
                return "Wrong username or password.";
            }
        } else {
            return "Please use syntax \"login <username> <password>\".";
        }
    }

    public String addSong() {
        SongDao songDao = new SongDao();

        String songName = splitCommand[2].replace("\"", "");
        String songDescription = splitCommand[3].replace("\"", "");
        String[] artists = splitCommand[4].split(",");
        String[] genres = splitCommand[5].split(",");
        String link = splitCommand[6].replace("\"", "");
        String addedBy = splitCommand[0];

        Song song = new Song();
        song.setName(songName);
        song.setDescription(songDescription);
        song.setLink(link);
        song.setAddedBy(addedBy);

        for (String art : artists) {
            Artist artist = new Artist();
            artist.setName(art);
            song.addArtist(artist);
        }

        for (String genre : genres) {
            Genre gen = new Genre();
            gen.setName(genre.trim().replace("\"", ""));
            song.addGen(gen);
        }

        if (songDao.create(song)) {
            return "Song added successfully.";
        } else {
            return "Song could not be added.";
        }
    }

    public String addComment() {
        CommentDao commentDao = new CommentDao();

        Comment comment = new Comment();
        comment.setUsername(splitCommand[0]);
        comment.setSongId(Integer.valueOf(splitCommand[2]));
        comment.setComment(splitCommand[3]);

        if (commentDao.create(comment)) {
            return "Comment added.";
        } else {
            return "Couldn't add comment.";
        }
    }

    public String getComments() {

        if (splitCommand.length < 3) {
            return "Please use syntax \"comments for <\"genre_name\">\".";
        }

        CommentDao commentDao = new CommentDao();

        List<Comment> commentList;
        commentList = commentDao.findBySongId(splitCommand[2]);

        CommentFormatter formatter = new CommentFormatter();

        return formatter.format(commentList, splitCommand[2]).toString();
    }

    public String getGeneralTop() {
        SongDao songDao = new SongDao();
        List<Song> songList;
        songList = songDao.getByVotes();
        SongFormatter songFormatter = new SongFormatter();

        return songFormatter.format(songList).toString();
    }

    public String getGenreTop() {
        SongDao songDao = new SongDao();
        List<Song> songList;
        songList = songDao.getForGenre(splitCommand[2].replace("\"", ""));
        SongFormatter songFormatter = new SongFormatter();

        return songFormatter.format(songList).toString();
    }

    private String generateHashedPassword(String cleanPassword) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(cleanPassword.getBytes(StandardCharsets.UTF_8));
        byte[] bytePassword = md.digest();

        String hashedPassword = "";

        for (int i = 0; i < bytePassword.length; i++) {
            hashedPassword += Integer.toHexString(0xFF & bytePassword[i]);
        }
        return hashedPassword;
    }
}
