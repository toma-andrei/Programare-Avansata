package commands.adminCommands;

import commands.Operation;

public class RestrictSongAdd implements Operation {

    AdminOperation adminOp;

    public RestrictSongAdd(AdminOperation operation) {
        adminOp = operation;
    }

    @Override
    public String execute() {
        return adminOp.restrictSongAdd();
    }
}
