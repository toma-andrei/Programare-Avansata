package server;

import java.util.Timer;

public class Timeout extends Thread {
    private ClientThread client;

    public Timeout(ClientThread client) {
        this.client = client;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep( 5 * 60 * 1000);
                if (client.getSleepingStatus() + 10000 < System.currentTimeMillis()) {
                    client.interrupt();
                    client.getWriter().println("Time limit exceeded!");
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
