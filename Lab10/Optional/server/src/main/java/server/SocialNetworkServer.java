package server;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

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
                clients.forEach(c -> System.out.println(c.getName()));
                clients.add(new Client());
                new ClientThread(socket, this, clients.get(clients.size() - 1)).start();
            }

        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println(graph.toString());
            System.out.println();
            
            String svg = SVGDOMImplementation.SVG_NAMESPACE_URI;
            DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
            Document doc = impl.createDocument(svg, "svg", null);
            Element svgRoot = doc.getDocumentElement();

            svgRoot.setAttributeNS(null, "width", "600");
            svgRoot.setAttributeNS(null, "height", "600");

            int x1 = 100;
            int y1 = 10;
            for (Client client : graph.vertexSet()) {
                Element text = doc.createElementNS(svg, "text");
                text.setAttributeNS(null, "x", String.valueOf(x1));
                text.setAttributeNS(null, "y", String.valueOf(y1));
                text.setAttributeNS(null, "text", client.getName());

                int x2 = x1 - 50;
                int y2 = y1 + 50;

                for (Client friend : client.getFriends()) {
                    Element friendText = doc.createElementNS(svg, "text");
                    friendText.setAttributeNS(null, "x", String.valueOf(x2));
                    friendText.setAttributeNS(null, "y", String.valueOf(y2));
                    friendText.setAttributeNS(null, "text", client.getName());

                    x2 += 30;
                    svgRoot.appendChild(friendText);
                }

                svgRoot.appendChild(text);
                x1 += 300;

                if (x1 >= 400) {
                    x1 = 100;
                    y1 += 100;
                }
            }

            try {
                SVGGraphics2D svgGenerator = new SVGGraphics2D(doc);
                Writer out = new OutputStreamWriter(System.out, "UTF-8");
                svgGenerator.stream(out);
            } catch (UnsupportedEncodingException unsupportedEncodingException) {
                unsupportedEncodingException.printStackTrace();
            } catch (SVGGraphics2DIOException svgGraphics2DIOException) {
                svgGraphics2DIOException.printStackTrace();
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
