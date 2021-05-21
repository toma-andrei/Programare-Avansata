package Server;

import commands.*;
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
    private int currentUserId;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader clientInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter msgToClient = new PrintWriter(socket.getOutputStream());

            String userCommand = "";
            String[] splitCommand;
            StringBuilder answer = new StringBuilder("");
            UserRepository userRepo = new UserRepository();

            UserCommandExecutor commandExecutor = new UserCommandExecutor();

            do {
                userCommand = clientInput.readLine();
                splitCommand = userCommand.split(" (?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                System.out.println(userCommand);

                if (splitCommand[0].equals("register")) {

                    answer.append(commandExecutor.executeOperation(new Register(new UserOperation(splitCommand))));

                } else if (splitCommand[0].equals("login")) {

                    answer.append(commandExecutor.executeOperation(new Login(new UserOperation(splitCommand))));


                    if (answer.toString().contains("Success")) {
                        loggedIn = true;
                        currentUserId = userRepo.findByName(splitCommand[1]).getId();
                    }

                } else if (!loggedIn) {

                    answer.append("You are not logged in.");

                } else if (splitCommand[0].equals("add")) {
                    if (splitCommand[1].equals("song")) {
                        if (splitCommand.length == 7) {
//                            String songName = splitCommand[2].replace("\"", "");
//                            String songDescription = splitCommand[3].replace("\"", "");
//                            String[] artists = splitCommand[4].split(",");
//                            String[] genres = splitCommand[5].split(",");
//                            String link = splitCommand[6].replace("\"", "");
//
//                            Song song = new Song();
//                            song.setName(songName);
//                            song.setDescription(songDescription);
//                            song.setLink(link);
//
//                            for (String art : artists) {
//                                Artist artist = new Artist();
//                                artist.setName(art);
//                                song.addArtist(artist);
//                            }
//
//                            for (String genre : genres) {
//                                Genre gen = new Genre();
//                                gen.setName(genre.trim().replace("\"", ""));
//                                song.addGen(gen);
//                            }
//
//                            if (songDao.create(song)) {
//                                answer.append("Song added successfully.");
//                            } else {
//                                answer.append("Song could not be added.");
//                            }
                            splitCommand[0] = String.valueOf(currentUserId);
                            answer.append(commandExecutor.executeOperation(new AddSong(new UserOperation(splitCommand))));

                        } else {

                            answer.append("Please use syntax \"add song <\"songName\"> <\"songDescription\"> <\"Artist1,Artist2,..\"> <\"genre1,genre2,..\"> <\"link\">\".");

                        }
                    } else if (splitCommand[1].equals("comment")) {

                    }
                } else if (splitCommand[0].equals("top")) {

                    if (splitCommand[1].equals("general")) {

                        answer.append(commandExecutor.executeOperation(new TopGeneral(new UserOperation(splitCommand))));

                    } else if (splitCommand[1].equals("for")) {

                        //TODO implementation

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


}
