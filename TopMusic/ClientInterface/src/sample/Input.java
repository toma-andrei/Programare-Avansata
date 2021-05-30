package sample;

import java.util.ArrayList;
import java.util.List;

public class Input {
    private static final List<String> command = new ArrayList<>();

    public List<String> getCommand() {
        return command;
    }

    public void addElement(String elem) {
        command.add(elem);
    }

    public void clean(){
        command.clear();
    }
}
