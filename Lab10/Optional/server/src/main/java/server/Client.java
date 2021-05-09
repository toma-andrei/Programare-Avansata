package server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client {
    String name = "";
    List<Client> friends = new ArrayList<>();
    Map<String, StringBuilder> messages = new HashMap<>();

    public void setName(String name) {
        this.name = name;
    }

    public void addFriend(Client friend) {
        friends.add(friend);
    }

    public void addMessage(String from, StringBuilder message) {
        messages.put(from, message);
    }

    public StringBuilder getMessages() {
        StringBuilder msgToClient = new StringBuilder();

        for (Map.Entry<String, StringBuilder> entry : messages.entrySet()) {
            msgToClient.append(entry.getKey());
            msgToClient.append(": ");
            msgToClient.append(entry.getValue());
            msgToClient.append("/LNSEP/");
        }
        return msgToClient;
    }

    public List<Client> getFriends() {
        return friends;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }
}
