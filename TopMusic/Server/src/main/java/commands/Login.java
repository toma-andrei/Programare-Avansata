package commands;

public class Login implements Operation{

    UserOperation userOp;

    public Login(UserOperation operation){
        userOp = operation;
    }

    @Override
    public String execute() {
        return userOp.login();
    }
}
