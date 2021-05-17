package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TopMusicServer {

    public static final int PORT = 8062;
    public static boolean serverRuns = true;
    ServerSocket serverSocket = null;
    ThreadPoolExecutor executor;
    int noOfThreads = 100;
    static int songId = 1;

    public TopMusicServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(noOfThreads);
            while (serverRuns) {
                Socket socket = serverSocket.accept();

                if (executor.getActiveCount() < noOfThreads) {
                    executor.execute(new ClientHandler(socket));
                } else {
                    Thread t = new Thread(new ClientHandler(socket));
                    t.start();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized int getSongId() {
        songId++;
        return songId-1;
    }

    public static void main(String[] args) {
        TopMusicServer server = new TopMusicServer();
    }
}
