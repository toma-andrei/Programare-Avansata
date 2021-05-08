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
    private Long lastActiveTime = System.currentTimeMillis();
    private SocialNetworkServer server;
    BufferedReader clientInput;
    PrintWriter msgToClient;

    public ClientThread(Socket socket, SocialNetworkServer server, Client client) {

        this.socket = socket;
        this.client = client;
        this.server = server;

        try {
            clientInput = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            msgToClient = new PrintWriter(this.socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void run() {
        System.out.println("Handle-uesc un Client!");
        server.clientIn();
        new Timeout(this).start();
        try {

            String request = "";
            String[] requestContent;
            StringBuilder answer;

            do {
                lastActiveTime = System.currentTimeMillis();
                request = clientInput.readLine();

                requestContent = request.split(" ");

                if (requestContent.length > 1 || (requestContent.length == 1 && (requestContent[0].equals("read") || requestContent[0].equals("exit") || requestContent[0].equals("stop"))))
                    if (requestContent[0].equals("register")) {
                        if (server.existClient(requestContent[1]))
                            answer = new StringBuilder("An user with this name already exist!");
                        else {
                            client.setName(requestContent[1]);
                            answer = new StringBuilder("Register Successfully!");
                        }
                    } else if (requestContent[0].equals("login")) {

                        if (isLoggedIn) {
                            answer = new StringBuilder("You are logged in!");
                        } else {
                            if (server.existClient(requestContent[1])) {
                                isLoggedIn = true;
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
                        answer = new StringBuilder("Your messages: /LNSEP/" + client.getMessages());

                    } else if (requestContent[0].equals("exit")) {
                        answer = new StringBuilder("exit");
                    } else if (requestContent[0].equals("stop")) {
                        answer = new StringBuilder("Server stopped");
                        msgToClient.println(answer);
                        msgToClient.flush();
                        server.stopServer();
                        break;
                    } else {
                        answer = new StringBuilder("Unknown command!");
                    }
                else
                    answer = new StringBuilder("Too few arguments!");

                System.out.println(answer);
                msgToClient.println(answer);
                msgToClient.flush();


            } while (!requestContent[0].equals("exit"));

            System.out.println("Im out!");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            server.clientOut();
        }
    }

    public Long getSleepingStatus() {
        return lastActiveTime;
    }

    public PrintWriter getWriter() {
        return msgToClient;
    }
}
