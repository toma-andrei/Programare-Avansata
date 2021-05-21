package commands;

public class TopGeneral implements Operation{

    UserOperation userOp;

    public TopGeneral(UserOperation operation){
        userOp = operation;
    }

    @Override
    public String execute() {
        return userOp.getGeneralTop();
    }
}
