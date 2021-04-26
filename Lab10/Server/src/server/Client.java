package server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client {
    String name;
    Socket socket;
    List<String> friends = new ArrayList<>();
    Map<String, String> messages = new HashMap<>();

    public void setName(String name) {
        this.name = name;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void addFriend(String friend) {
        friends.add(friend);
    }

    public void addMessage(String from, String message) {
        messages.put(from, message);
    }

    public String getMessages() {
        String msgToClient = "";

        for (Map.Entry<String, String> entry : messages.entrySet()) {
            msgToClient = entry.getKey() + ": " + entry.getValue() + "\n";
        }
        return msgToClient;
    }
}
