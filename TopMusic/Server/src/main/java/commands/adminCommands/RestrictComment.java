package commands.adminCommands;

import commands.Operation;

public class RestrictComment implements Operation {
    AdminOperation adminOp;

    public RestrictComment(AdminOperation operation) {
        adminOp = operation;
    }

    @Override
    public String execute() {
        return adminOp.restrictComment();
    }
}
