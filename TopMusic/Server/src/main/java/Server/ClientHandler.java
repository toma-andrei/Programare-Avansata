package Server;

import java.net.Socket;

public class ClientHandler extends Thread{
    //TODO implementation
    private Socket socket;

    public ClientHandler(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try {
            System.out.println("Doing stuff...");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("im out!!!");
    }
}
