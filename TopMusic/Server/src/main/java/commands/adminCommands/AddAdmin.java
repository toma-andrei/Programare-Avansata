package commands.adminCommands;

import commands.Operation;

public class AddAdmin implements Operation {
    AdminOperation adminOp;

    public AddAdmin(AdminOperation operation) {
        this.adminOp = operation;
    }

    @Override
    public String execute() {
        return adminOp.addAdmin();
    }
}
