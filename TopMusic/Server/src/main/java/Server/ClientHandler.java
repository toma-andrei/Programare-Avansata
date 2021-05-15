package Server;

import entities.User;
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

            do {
                userCommand = clientInput.readLine();
                splitCommand = userCommand.split(" (?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                if (splitCommand[0].equals("register")) {
                    if (splitCommand.length == 3) {
                        if (!UserRepository.usernameExists(splitCommand[1])) {
                            User user = new User();
                            user.setUsername(splitCommand[1]);
                            user.setPassword(generateHashedPassword(splitCommand[2]));
                            userRepo.create(user);
                            answer.append("Congratulations, your account has been successfully created.");
                        }
                        else{
                            answer.append("This username is already taken. Please choose another name.");
                        }
                    }
                }

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
