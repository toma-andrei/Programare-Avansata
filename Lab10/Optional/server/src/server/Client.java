package server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client {
    String name;
    Socket socket;
    List<Client> friends = new ArrayList<>();
    Map<String, StringBuilder> messages = new HashMap<>();

    public void setName(String name) {
        this.name = name;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void addFriend(Client friend) {
        friends.add(friend);
    }

    public void addMessage(String from, StringBuilder message) {
        messages.put(from, message);
    }

    public String getMessages() {
        String msgToClient = "";

        for (Map.Entry<String, StringBuilder> entry : messages.entrySet()) {
            msgToClient = entry.getKey() + ": " + entry.getValue() + "\n";
        }
        return msgToClient;
    }

    public List<Client> getFriends() {
        return friends;
    }

    public String getName() {
        return name;
    }
}
