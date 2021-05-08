package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        final int PORT = 8123;
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

                responseFromServer = inputFromServer.readLine();

                responseFromServer = responseFromServer.replace("/LNSEP/", System.lineSeparator());

                if (responseFromServer.equals("exit") || responseFromServer.equals("Server stopped") || responseFromServer.equals("Time limit exceeded!")) {
                    exit = true;
                    System.out.println(responseFromServer);
                } else {
                    System.out.println(responseFromServer);
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
