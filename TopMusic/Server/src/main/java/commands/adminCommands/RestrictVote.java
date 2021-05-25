package commands.adminCommands;

import commands.AdminOperation;
import commands.Operation;
import commands.userCommands.UserOperation;

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
