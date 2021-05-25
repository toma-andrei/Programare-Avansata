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
     * register username password                                                           DONE
     * login username password                                                              DONE
     * add song "songName" "songDescription" "Artist1,Artist2" "genre1,genre2" "link"       DONE
     * add comment idSong "comment"                                                         DONE
     * top general                                                                          DONE
     * top for "genre"                                                                      DONE
     * comments for songId                                                                  DONE
     * ***  ADMIN   ***
     * restrict vote for userId
     * restrict comment for userId
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

                msgToServer.println(userInput);
                msgToServer.flush();

                responseFromServer = inputFromServer.readLine();
                responseFromServer = responseFromServer.replace("/LNSEP/", System.lineSeparator());
                System.out.println(responseFromServer);
                System.out.println();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
