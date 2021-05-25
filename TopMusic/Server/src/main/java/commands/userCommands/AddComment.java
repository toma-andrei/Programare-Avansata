package commands.userCommands;

import commands.Operation;

public class AddComment implements Operation {

    UserOperation userOp;

    public AddComment(UserOperation operation){
        userOp = operation;
    }

    @Override
    public String execute() {
        return userOp.addComment();
    }
}
