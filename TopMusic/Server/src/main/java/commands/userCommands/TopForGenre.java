package commands.userCommands;


import commands.Operation;

public class TopForGenre implements Operation {
    UserOperation userOp;

    public TopForGenre(UserOperation operation){
        userOp = operation;
    }

    @Override
    public String execute() {
        return userOp.getGenreTop();
    }
}