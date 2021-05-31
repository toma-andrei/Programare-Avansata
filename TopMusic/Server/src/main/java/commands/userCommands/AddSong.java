package commands.userCommands;

import commands.Operation;

public class AddSong implements Operation {

    UserOperation userOp;

    public AddSong(UserOperation operation){
        userOp = operation;
    }

    @Override
    public String execute() {
        return userOp.addSong();
    }
}