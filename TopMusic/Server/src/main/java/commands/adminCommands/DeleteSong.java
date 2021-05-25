package commands.adminCommands;

import commands.Operation;

public class DeleteSong implements Operation {

    AdminOperation adminOp;

    public DeleteSong(AdminOperation operation) {
        this.adminOp = operation;
    }

    @Override
    public String execute() {
        return adminOp.deleteSong();
    }
}
