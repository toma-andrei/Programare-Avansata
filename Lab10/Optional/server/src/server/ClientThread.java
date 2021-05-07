package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientThread extends Thread {

    private Socket socket;
    private Client client;
    private boolean isLoggedIn = false;
    private SocialNetworkServer server;

    public ClientThread(Socket socket, SocialNetworkServer server) {

        this.socket = socket;
        client = new Client();
        client.setSocket(socket);
        this.server = server;
    }

    public void run() {
        System.out.println("Handle-uesc un Client!");
        server.clientIn();

        try {
            BufferedReader clientInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter msgToClient = new PrintWriter(socket.getOutputStream());

            String request = "";
            String[] requestContent;
            StringBuilder answer;

            do {

                request = clientInput.readLine();

                requestContent = request.split(" ");

                if (requestContent[0].equals("register")) {
                    client.setName(requestContent[1]);
                    client.setSocket(socket);
                    server.addClient(client);
                    answer = new StringBuilder("Register Successfully!");
                } else if (requestContent[0].equals("login")) {

                    if (isLoggedIn) {
                        answer = new StringBuilder("You are logged in!");
                    } else {
                        List<Client> clientList = server.getClientList();
                        for (Client c : clientList) {
                            if (c.getName().equals(client.getName())) {
                                isLoggedIn = true;
                            }
                        }

                        if (isLoggedIn) {
                            answer = new StringBuilder("You logged in!");
                        } else {
                            answer = new StringBuilder("User with this username does not exist! Please register!");
                        }
                    }

                } else if (!isLoggedIn) {
                    answer = new StringBuilder("You must be logged in!");
                } else if (requestContent[0].equals("friend")) {
                    int len = requestContent.length;
                    List<String> nonExistingFriends = new ArrayList<>();
                    for (int i = 1; i < len; i++) {
                        if (server.existClient(requestContent[i])) {
                            client.addFriend(server.getClientByName(requestContent[i]));
                        } else {
                            nonExistingFriends.add(requestContent[i]);
                        }
                    }

                    answer = new StringBuilder("You added " + (len - 1 - nonExistingFriends.size()) + " friend(s)!");

                    if (nonExistingFriends.size() > 0) {
                        answer.append(" No registered user with name");
                        for (String friend : nonExistingFriends) {
                            answer.append(" ");
                            answer.append(friend);
                        }
                        answer.append("!");
                    }

                } else if (requestContent[0].equals("send")) {
                    StringBuilder message = new StringBuilder();

                    int len = requestContent.length;

                    message.append(requestContent[1]);

                    for (int i = 2; i < len; i++) {
                        message.append(" ");
                        message.append(requestContent[i]);
                    }

                    synchronized (this) {
                        for (Client friend : client.getFriends()) {
                            friend.addMessage(client.getName(), message);
                        }
                    }

                    answer = new StringBuilder("Message sent!");

                } else if (requestContent[0].equals("read")) {
                    answer = new StringBuilder("Your messages: " + client.getMessages());

                } else if (requestContent[0].equals("exit")) {
                    answer = new StringBuilder("exit");
                } else if (requestContent[0].equals("stop")) {
                    answer = new StringBuilder("Server stopped");
                    msgToClient.println(answer);
                    msgToClient.flush();
                    server.stopServer();
                    server.clientOut();
                } else {
                    answer = new StringBuilder("Unknown command!");
                }

                System.out.println(answer);
                msgToClient.println(answer);
                msgToClient.flush();


            } while (!requestContent[0].equals("exit"));

            msgToClient.println(request);

            System.out.println("Im out!!");
        } catch (
                IOException e) {
            e.printStackTrace();
        } finally {
            server.clientOut();
        }
    }
}
