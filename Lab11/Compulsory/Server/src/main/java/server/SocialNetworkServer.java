//package server;
//
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.List;
//
//public class SocialNetworkServer {
//
//    public static final int PORT = 8123;
//    public static boolean serverRuns = true;
//    ServerSocket serverSocket = null;
//
//    private static List<Client> clients = new ArrayList<>();
//
//    public SocialNetworkServer() {
//        try {
//            serverSocket = new ServerSocket(PORT);
//
//            while (serverRuns) {
//                System.out.println("Waiting for clients...");
//                Socket socket = serverSocket.accept();
//                new ClientThread(socket, this).start();
//            }
//
//            System.out.println("Server stopped!");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                serverSocket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public synchronized void addClient(Client client) {
//        clients.add(client);
//    }
//
//    public synchronized List<Client> getClientList() {
//        return clients;
//    }
//
//    public synchronized void stopServer() {
//        serverRuns = false;
//        System.out.println("m-am oprit!");
//        System.exit(0);
//    }
//
//    public static void main(String[] args) {
//        SocialNetworkServer server = new SocialNetworkServer();
//    }
//}
