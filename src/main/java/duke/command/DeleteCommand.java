package duke.command;

import duke.components.Command;
import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;

public class DeleteCommand extends Command {
    private String userInput;
    private String[] userInputArray;

    public DeleteCommand(String fullCommand) {
        userInput = fullCommand;
        userInputArray = fullCommand.split(" ");
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.delete(Integer.parseInt(userInputArray[1]));
        } catch (IndexOutOfBoundsException e) {
            ui.showError("☹ OOPS!!! A task with that ID does not exist.");
        }
    }

    public boolean isExit() {
        return false;
    }
}
