package Server;

import commands.*;
import commands.adminCommands.*;
import commands.userCommands.*;
import entities.User;
import repositories.UserRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ResourceBundle;

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
            String configFile = "res.Messages";
            ResourceBundle messages = null;

            if (loggedIn) {
                messages = ResourceBundle.getBundle(configFile);
            }

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

                    String englishAnswer = commandExecutor.executeOperation(new Login(new UserOperation(splitCommand)));

                    if (englishAnswer.contains("Success")) {
                        loggedIn = true;
                        user = userRepo.findByName(splitCommand[1]);
                        SetLocale.set(user.getLocale());

                        messages = ResourceBundle.getBundle(configFile);

                        answer.append(messages.getString("loginSuccessful"));

                    } else if (englishAnswer.contains("Wrong")){
                        messages = ResourceBundle.getBundle(configFile);

                        answer.append(messages.getString("wrongInput"));
                    }
                    else{
                        answer.append(messages.getString("wrongSyntax")).append(" \"login <username> <password>\".");
                    }

                } else if (!loggedIn) {

                    answer.append(messages.getString("notLoggedIn"));

                } else if (splitCommand[0].equals("add")) {
                    if (splitCommand[1].equals("song")) {
                        if (splitCommand.length == 7) {

                            splitCommand[0] = String.valueOf(user.getId());
                            answer.append(commandExecutor.executeOperation(new AddSong(new UserOperation(splitCommand))));

                        } else {
                            answer.append(messages.getString("wrongSyntax")).append(" \"add song <\"songName\"> <\"songDescription\"> <\"Artist1,Artist2,..\"> <\"genre1,genre2,..\"> <\"link\">\".");

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
                        answer.append(messages.getString("wrongSyntax")).append(" \"top general\" OR \"top for <\"genre\">.");

                    }
                } else if (splitCommand[0].equals("comments")) {

                    answer.append(commandExecutor.executeOperation((new GetComments(new UserOperation(splitCommand)))));

                } else if (!user.getAdmin().equals(0)) {
                    if (splitCommand[0].equals("restrict")) {
                        if (splitCommand[1].equals("votes")) {

                            answer.append(commandExecutor.executeOperation(new RestrictVote(new AdminOperation(splitCommand))));

                        } else if (splitCommand[1].equals("comments")) {

                            answer.append(commandExecutor.executeOperation(new RestrictComment(new AdminOperation(splitCommand))));

                        } else if (splitCommand[1].equals("songAdd")) {

                            answer.append(commandExecutor.executeOperation(new RestrictSongAdd(new AdminOperation(splitCommand))));

                        } else {
                            answer.append(messages.getString("wrongSyntax")).append(" \"restrict votes|comments|songAdd for user_id\"");
                        }
                    } else if (splitCommand[0].equals("delete")) {
                        if (splitCommand[1].equals("song")) {
                            answer.append(commandExecutor.executeOperation(new DeleteSong(new AdminOperation(splitCommand))));
                        } else if (splitCommand[1].equals("comment")) {
                            answer.append(commandExecutor.executeOperation(new DeleteComment(new AdminOperation(splitCommand))));
                        }
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
