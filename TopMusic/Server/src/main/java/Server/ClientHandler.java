package Server;

import commands.*;
import entities.User;
import repositories.UserRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
                        user = userRepo.findByName(splitCommand[1]);
                    }

                } else if (!loggedIn) {

                    answer.append("You are not logged in.");

                } else if (splitCommand[0].equals("add")) {
                    if (splitCommand[1].equals("song")) {
                        if (splitCommand.length == 7) {

                            splitCommand[0] = String.valueOf(user.getId());
                            answer.append(commandExecutor.executeOperation(new AddSong(new UserOperation(splitCommand))));

                        } else {

                            answer.append("Please use syntax \"add song <\"songName\"> <\"songDescription\"> <\"Artist1,Artist2,..\"> <\"genre1,genre2,..\"> <\"link\">\".");

                        }
                    } else if (splitCommand[1].equals("comment")) {
                        splitCommand[0] = user.getUsername();
                        answer.append(commandExecutor.executeOperation(new AddComment(new UserOperation(splitCommand))));
                    }
                } else if (splitCommand[0].equals("top")) {

                    if (splitCommand[1].equals("general")) {

                        answer.append(commandExecutor.executeOperation(new TopGeneral(new UserOperation(splitCommand))));

                    } else if (splitCommand[1].equals("for")) {

                        answer.append(commandExecutor.executeOperation(new TopForGenre(new UserOperation(splitCommand))));
                    } else {

                        answer.append("Please use syntax \"top general\" OR \"top for <\"genre\">.");

                    }
                } else if (splitCommand[0].equals("comments")) {

                    answer.append(commandExecutor.executeOperation((new GetComments(new UserOperation(splitCommand)))));

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
