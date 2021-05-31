package commands.userCommands;

import commands.Operation;

public class Vote implements Operation {

    UserOperation userOp;

    public Vote(UserOperation operation) {
        userOp = operation;
    }

    @Override
    public String execute() {
        return userOp.vote();
    }
}
