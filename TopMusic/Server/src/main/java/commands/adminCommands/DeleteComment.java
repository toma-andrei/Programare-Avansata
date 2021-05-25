package commands.adminCommands;

import commands.Operation;

public class DeleteComment implements Operation {
    AdminOperation adminOp;

    public DeleteComment(AdminOperation operation){
        this.adminOp = operation;
    }

    @Override
    public String execute() {
        return adminOp.deleteComment();
    }
}
