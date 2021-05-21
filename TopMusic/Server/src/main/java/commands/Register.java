package commands;

public class Register implements Operation{
    UserOperation userOp;

    public Register(UserOperation operation){
        userOp = operation;
    }

    @Override
    public String execute() {
        return userOp.register();
    }
}
