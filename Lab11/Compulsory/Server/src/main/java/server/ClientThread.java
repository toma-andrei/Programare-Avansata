//package server;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.Socket;
//
//public class ClientThread extends Thread {
//
//    private Socket socket;
//    private Client client;
//    private boolean isLoggedIn = false;
//    private SocialNetworkServer server;
//
//    public ClientThread(Socket socket, SocialNetworkServer server) {
//        this.socket = socket;
//        client = new Client();
//        client.setSocket(socket);
//        this.server = server;
//    }
//
//    public void run() {
//        System.out.println("Handleuesc un Client!!");
//
//        try {
//            BufferedReader clientInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            PrintWriter msgToClient = new PrintWriter(socket.getOutputStream());
//
//            String request = "";
//            String[] requestContent;
//            String answer = "";
//
//            do {
//
//                request = clientInput.readLine();
//
//                requestContent = request.split(" ");
//
//                if (requestContent[0].equals("register")) {
//                    answer = "Register Successfully!";
//                } else if (requestContent[0].equals("login") && !isLoggedIn) {
//                    answer = "You logged in!";
//                    isLoggedIn = true;
//                } else if (!isLoggedIn) {
//                    answer = "You must be logged in!";
//                } else if (requestContent[0].equals("friend")) {
//                    answer = "You added friends!";
//                } else if (requestContent[0].equals("send")) {
//                    answer = "Message sent!";
//                } else if (requestContent[0].equals("read")) {
//                    answer = "Your messages: ... ";
//                } else if (requestContent[0].equals("exit")) {
//                    answer = "exit";
//                } else if (requestContent[0].equals("stop")) {
//                    answer = "Server stopped";
//                    msgToClient.println(answer);
//                    msgToClient.flush();
//                    server.stopServer();
//                } else {
//                    answer = "Unknown command!";
//                }
//
//                System.out.println(answer);
//                msgToClient.println(answer);
//                msgToClient.flush();
//
//
//            } while (!requestContent[0].equals("exit"));
//
//
//            msgToClient.println(request);
//
//            System.out.println("Im out!!");
//        } catch (
//                IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
