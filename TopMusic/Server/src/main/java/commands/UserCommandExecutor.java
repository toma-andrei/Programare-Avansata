package commands;

public class UserCommandExecutor {

    public String executeOperation(Operation command) {
        return command.execute();
    }
}
