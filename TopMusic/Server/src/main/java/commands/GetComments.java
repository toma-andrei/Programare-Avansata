package commands;

public class GetComments implements Operation{
    UserOperation userOp;

    public GetComments(UserOperation operation){
        userOp = operation;
    }

    @Override
    public String execute() {
        return userOp.getComments();
    }
}
