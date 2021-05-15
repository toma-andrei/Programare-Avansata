package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    /**
     * Commands be like:
     * <p>
     * register username password
     * login username password
     * add song "songName" "songDescription" "Artist1,Artist2" "link"
     * add comment idSong "comment"
     * top general
     * top for "genre"
     * comments for songId
     * ***  ADMIN   ***
     * restrict votes for userId
     * restrict comments for userId
     * restrict songAdd for userId
     * delete song songId
     * delete comment commentId
     */

    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        final int PORT = 8062;
        boolean exit = false;

        try {

            Socket socket = new Socket(serverAddress, PORT);

            PrintWriter msgToServer = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner scan = new Scanner(System.in);

            String userInput;

            String responseFromServer;

            while (!exit) {
                userInput = scan.nextLine();

                // scrie mesajul catre server
                msgToServer.println(userInput);

                // asteapta mesajul de la server si-l citeste cand ajunge
//                responseFromServer = inputFromServer.readLine();
//
//                if (responseFromServer.equals("exit") || responseFromServer.equals("Server stopped")) {
//                    exit = true;
//                    System.out.println(responseFromServer);
//                } else {
//                    System.out.println(responseFromServer);
//                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
