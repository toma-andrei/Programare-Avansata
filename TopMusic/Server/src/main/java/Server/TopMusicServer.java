package Server;

import com.mysql.cj.xdevapi.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TopMusicServer {

    public static final int PORT = 8062;
    public static boolean serverRuns = true;
    ServerSocket serverSocket = null;

    public TopMusicServer() {
        try {
            serverSocket = new ServerSocket(PORT);

            while (serverRuns) {
                Socket socket = serverSocket.accept();
               new ClientHandler(socket).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TopMusicServer server = new TopMusicServer();
    }
}
