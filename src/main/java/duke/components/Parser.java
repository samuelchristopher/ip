package duke.components;

import duke.command.*;
import duke.exception.InvalidCommandException;

public class Parser {
    public static Command parse(String fullCommand) {
        String[] userInputArray = (fullCommand.split(" "));
        String command = userInputArray[0];
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("todo")) {
            return new TodoCommand(fullCommand);
        } else if (command.equals("event")) {
            return new EventCommand(fullCommand);
        } else if (command.equals("deadline")) {
            return new DeadlineCommand(fullCommand);
        } else if (command.equals("delete")) {
            return new DeleteCommand(fullCommand);
        } else if (command.equals("done")) {
            return new DoneCommand(fullCommand);
        } else {
            try {
                throw new InvalidCommandException();
            } catch (InvalidCommandException e) {
                Ui.showError(InvalidCommandException.errorMessage());
            }
        }
        return new Command();
    }

}
