package server;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocialNetworkServer {

    public static final int PORT = 8123;
    public static boolean serverRuns = true;
    public static Graph<Client, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);
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
                clients.add(new Client());
                new ClientThread(socket, this, clients.get(clients.size() - 1)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(graph.toString());
            System.out.println();

            File myimg = new File("image0.png");
            if(myimg.exists())
            {
                myimg.delete();
            }
            try {
                myimg.createNewFile();
            } catch (IOException exception) {
                exception.printStackTrace();
            }

            JGraphXAdapter<Client, DefaultEdge> graphAdapter =
                    new JGraphXAdapter<>(graph);
            mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
            layout.execute(graphAdapter.getDefaultParent());

            BufferedImage image =
                    mxCellRenderer.createBufferedImage(graphAdapter, null, 2, Color.WHITE, true, null);

            try {
                ImageIO.write(image, "PNG", myimg);
            } catch (IOException exception) {
                exception.printStackTrace();
            }

            while (onlineClients > 0) {
                System.out.println(onlineClients);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
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

    public synchronized void addNode(Client c) {
        graph.addVertex(c);
    }

    public synchronized void addEdge(Client client) {
        for (Client friend : client.getFriends()) {
            graph.addEdge(client, friend);
        }
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
