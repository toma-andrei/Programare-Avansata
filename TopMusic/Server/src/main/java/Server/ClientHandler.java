package Server;

import daoClasses.SongDao;
import entities.Artist;
import entities.Genre;
import entities.Song;
import entities.User;
import formatter.SongFormatter;
import org.hibernate.loader.plan.build.internal.CascadeStyleLoadPlanBuildingAssociationVisitationStrategy;
import repositories.SongRepository;
import repositories.UserRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private boolean loggedIn = false;
    private User user;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader clientInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter msgToClient = new PrintWriter(socket.getOutputStream());

            String userCommand = "";
            String[] splitCommand = new String[0];
            StringBuilder answer = new StringBuilder("");
            UserRepository userRepo = new UserRepository();
            SongRepository songRepo = new SongRepository();
            SongDao songDao = new SongDao();

            do {
                userCommand = clientInput.readLine();
                splitCommand = userCommand.split(" (?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                System.out.println(userCommand);
                if (splitCommand[0].equals("register")) {
                    if (splitCommand.length == 3) {
                        if (!UserRepository.usernameExists(splitCommand[1])) {
                            User user = new User();
                            user.setUsername(splitCommand[1]);
                            user.setPassword(generateHashedPassword(splitCommand[2]));
                            userRepo.create(user);
                            answer.append("Congratulations, your account has been successfully created.");
                        } else {
                            answer.append("This username is already taken. Please choose another name.");
                        }
                    } else {
                        answer.append("Please use syntax \"register <username> <password>\".");
                    }
                } else if (splitCommand[0].equals("login")) {
                    if (splitCommand.length == 3) {
                        if (userRepo.correctLoginInput(splitCommand[1], generateHashedPassword(splitCommand[2]))) {
                            answer.append("Login Successful.");
                            loggedIn = true;
                        } else {
                            answer.append("Wrong username or password.");
                        }
                    } else {
                        answer.append("Please use syntax \"login <username> <password>\".");
                    }
                } else if (!loggedIn) {
                    answer.append("You are not logged in.");
                } else if (splitCommand[0].equals("add")) {
                    if (splitCommand[1].equals("song")) {
                        if (splitCommand.length == 7) {
                            String songName = splitCommand[2].replace("\"", "");
                            String songDescription = splitCommand[3].replace("\"", "");
                            String[] artists = splitCommand[4].split(",");
                            String[] genres = splitCommand[5].split(",");
                            String link = splitCommand[6].replace("\"", "");

                            Song song = new Song();
                            song.setName(songName);
                            song.setDescription(songDescription);
                            song.setLink(link);

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
                                answer.append("Song added successfully.");
                            } else {
                                answer.append("Song could not be added.");
                            }
                        } else {
                            answer.append("Please use syntax \"add song <\"songName\"> <\"songDescription\"> <\"Artist1,Artist2,..\"> <\"genre1,genre2,..\"> <\"link\">\".");
                        }
                    } else if (splitCommand[1].equals("comment")) {

                    }
                } else if (splitCommand[0].equals("top")) {

                    if (splitCommand[1].equals("general")) {

                        List<Song> songList;
                        songList = songDao.getByVotes();
                        SongFormatter songFormatter = new SongFormatter();
                        answer.append(songFormatter.format(songList));

                    } else if (splitCommand[1].equals("for")) {

                    } else {
                        answer.append("Please use syntax \"top general\" OR \"top for <\"genre\">.");
                    }
                }
                msgToClient.println(answer);
                msgToClient.flush();
                answer.delete(0, answer.length());
            } while (!splitCommand[0].equals("exit"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String generateHashedPassword(String cleanPassword) {
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
