package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocialNetworkServer {

    public static final int PORT = 8123;
    public static boolean serverRuns = true;
    ServerSocket serverSocket = null;
    private int onlineClients = 0;

    private static List<Client> clients = new ArrayList<>();

    public SocialNetworkServer() {
        try {
            serverSocket = new ServerSocket(PORT);

            while (serverRuns) {
                System.out.println("Waiting for clients...");
                System.out.println(onlineClients);
                Socket socket = serverSocket.accept();
                clients.forEach(c -> System.out.println(c.getName()));
                clients.add(new Client());
                new ClientThread(socket, this, clients.get(clients.size() - 1)).start();
            }

        } catch (IOException e) {
            // e.printStackTrace();
            while (onlineClients > 0) {

            }
            System.out.println("Server closed!");
        }
    }

    public synchronized List<Client> getClientList() {
        return clients;
    }

    public synchronized void stopServer() throws IOException {
        serverRuns = false;
        serverSocket.close();
    }

    public synchronized void clientIn() {
        onlineClients++;
    }

    public synchronized void clientOut() {
        onlineClients--;
    }

    public synchronized boolean existClient(String name) {
        for (Client c : clients) {
            if (c.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public synchronized Client getClientByName(String name) {
        for (Client c : clients) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SocialNetworkServer server = new SocialNetworkServer();
    }
}
