package commands.userCommands;

import daoClasses.CommentDao;
import daoClasses.SongDao;
import entities.*;
import formatter.CommentFormatter;
import formatter.SongFormatter;
import repositories.UserRepository;
import Server.SetLocale;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ResourceBundle;

public class UserOperation {

    String[] splitCommand;

    public UserOperation(String[] splitCommand) {
        this.splitCommand = splitCommand;
    }

    public String register() {
        UserRepository userRepo = new UserRepository();
        ResourceBundle messages = null;

        if (splitCommand.length != 4) {
            return messages.getString("wrongSyntax") + " \"register <username> <password>\".";
        }

        SetLocale.set(splitCommand[3]);
        messages = ResourceBundle.getBundle("res.Messages");

        if (!UserRepository.usernameExists(splitCommand[1])) {

            User user = new User();
            user.setUsername(splitCommand[1]);
            user.setPassword(generateHashedPassword(splitCommand[2]));
            user.setLocale(splitCommand[3]);
            userRepo.create(user);

            return messages.getString("registerSuccess");

        } else {
            return messages.getString("usernameExist");
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
        ResourceBundle message = ResourceBundle.getBundle("res.Messages");
        if (songDao.create(song)) {

            return message.getString("songAddedSuccess");
        } else {
            return message.getString("songAddedFails");
        }
    }

    public String addComment() {
        CommentDao commentDao = new CommentDao();
        ResourceBundle message = ResourceBundle.getBundle("res.Messages");
        if (splitCommand.length != 4) {
            return message.getString("wrongSyntax") + (" \"add comment <id_song> <\"comment\">\".");
        }

        Comment comment = new Comment();
        comment.setUsername(splitCommand[0]);
        comment.setSongId(Integer.valueOf(splitCommand[2]));
        comment.setComment(splitCommand[3]);

        if (commentDao.create(comment)) {
            return message.getString("commentAdded");
        } else {
            return message.getString("commentNotAdded");
        }
    }

    public String getComments() {
        ResourceBundle message = ResourceBundle.getBundle("res.Messages");

        if (splitCommand.length < 3) {
            return message.getString("wrongSyntax") + " \"comments for <\"genre_name\">\".";
        }

        CommentDao commentDao = new CommentDao();

        List<Comment> commentList;
        commentList = commentDao.findBySongId(splitCommand[2]);

        CommentFormatter formatter = new CommentFormatter();

        return formatter.format(commentList, splitCommand[2], message).toString();
    }

    public String getGeneralTop() {
        SongDao songDao = new SongDao();
        List<Song> songList;
        songList = songDao.getByVotes();
        SongFormatter songFormatter = new SongFormatter();
        String configFile = "res.Messages";
        ResourceBundle messages = ResourceBundle.getBundle(configFile);
        return songFormatter.format(songList, messages).toString();
    }

    public String getGenreTop() {
        SongDao songDao = new SongDao();
        List<Song> songList;
        songList = songDao.getForGenre(splitCommand[2].replace("\"", ""));
        SongFormatter songFormatter = new SongFormatter();

        String configFile = "res.Messages";
        ResourceBundle messages = ResourceBundle.getBundle(configFile);

        return songFormatter.format(songList, messages).toString();
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
