package commands.adminCommands;

import commands.Operation;

public class RestrictVote implements Operation {
    AdminOperation adminOp;

    public RestrictVote(AdminOperation operation) {
        adminOp = operation;
    }

    @Override
    public String execute() {
        return adminOp.restrictVote();
    }
}
