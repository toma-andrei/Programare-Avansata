package server;

import entities.Genre;
import entities.Song;
import entities.User;
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

public class ClientHandler implements Runnable {
    private final Socket socket;
    private boolean loggedIn = false;

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
            UserRepository repository;

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
                            String songName = splitCommand[2];
                            String songDescription = splitCommand[3];
                            String artists = splitCommand[4];
                            String[] genres = splitCommand[5].split(",");
                            String link = splitCommand[6];

                            Song song = new Song();
                            song.setId(TopMusicServer.getSongId());
                            song.setName(songName);
                            song.setDescription(songDescription);
                            song.setArtists(artists);
                            song.setLink(link);

                            for (String genre : genres) {
                                Genre gen = new Genre();
                                gen.setIdSong(song.getId());
                                gen.setName(genre.trim());
//                                song.addGen(gen);
                            }

                            songRepo.create(song);

                        } else {
                            answer.append("Please use syntax \"add song <\"songName\"> <\"songDescription\"> <\"Artist1,Artist2,..\"> <\"genre1,genre2,..\"> <\"link\">.");

                        }
                    } else if (splitCommand[1].equals("comment")) {

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
